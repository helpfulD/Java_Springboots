package com.hand.demo.app.service.impl;

import com.hand.demo.domain.entity.SoLine;
import com.hand.demo.domain.repository.SoLineRepository;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.CustomUserDetails;
import io.choerodon.core.oauth.DetailsHelper;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import com.hand.demo.app.service.SoHeaderService;
import com.hand.demo.domain.entity.SoHeader;
import com.hand.demo.domain.repository.SoHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 应用服务默认实现
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Service
public class SoHeaderServiceImpl extends BaseAppService implements SoHeaderService {

    private final SoHeaderRepository soHeaderRepository;

    private final SoLineRepository soLineRepository;

    @Autowired
    public SoHeaderServiceImpl(SoHeaderRepository soHeaderRepository, SoLineRepository soLineRepository) {
        this.soHeaderRepository = soHeaderRepository;
        this.soLineRepository = soLineRepository;
    }

    @Override
    public Page<SoHeader> list(Long tenantId, SoHeader soHeader, PageRequest pageRequest) {
        return soHeaderRepository.pageAndSort(pageRequest, soHeader);
    }

    @Override
    public SoHeader detail(Long tenantId, Long soHeaderId) {
        return soHeaderRepository.selectByPrimaryKey(soHeaderId);
    }

    @Override
    public SoHeader create(Long tenantId, SoHeader soHeader) {
        validObject(soHeader);
        soHeaderRepository.insertSelective(soHeader);
        return soHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoHeader update(Long tenantId, SoHeader soHeader) {
        SecurityTokenHelper.validToken(soHeader);
        soHeaderRepository.updateByPrimaryKeySelective(soHeader);
        return soHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(SoHeader soHeader) {
        SecurityTokenHelper.validToken(soHeader);
        soHeaderRepository.deleteByPrimaryKey(soHeader);
    }

    @Override
    public Page<SoHeader> query(Long organizationId, SoHeader soHeader, PageRequest pageRequest) {


        return soHeaderRepository.query(organizationId, soHeader, pageRequest);
    }

    @Override
    public void stateControl(Long organizationId, SoHeader soHeader) {

        SoHeader soHeader1 = soHeaderRepository.selectByPrimaryKey(soHeader.getSoHeaderId());
        //获取当前用户id
        CustomUserDetails userDetails = DetailsHelper.getUserDetails();
        Long userId = userDetails.getUserId();

        if (soHeader.getOrderStatus().equals("SUBMITED")) {
            if (Objects.isNull(soHeader1))
                throw new CommonException("----------此订单不存在----------");
            if(!userId.equals(soHeader1.getCreatedBy()))
                throw new CommonException("----------当前用户与单据创建人不一致----------");
            if (!(soHeader1.getOrderStatus().equals("NEW") || soHeader1.getOrderStatus().equals("REJECTED")))
                throw new CommonException("----------订单状态非新建或非已拒绝,无法提交----------");
        }

        if (soHeader.getOrderStatus().equals("APPROVED")) {
            if (Objects.isNull(soHeader1))
                throw new CommonException("----------此订单不存在----------");
            if (!soHeader1.getOrderStatus().equals("SUBMITED"))
                throw new CommonException("----------订单状态非已提交,无法审批----------");
        }

        if (soHeader.getOrderStatus().equals("REJECTED")) {
            if (Objects.isNull(soHeader1))
                throw new CommonException("----------此订单不存在----------");
            if (!soHeader1.getOrderStatus().equals("SUBMITED"))
                throw new CommonException("----------订单状态非已提交,无法拒绝----------");
        }

        soHeaderRepository.updateByPrimaryKeySelective(soHeader);
    }

    @Override
    public void removeById(SoHeader soHeader) {
        SecurityTokenHelper.validToken(soHeader);
        SoHeader soHeader1 = soHeaderRepository.selectByPrimaryKey(soHeader.getSoHeaderId());
        //获取当前用户id
        CustomUserDetails userDetails = DetailsHelper.getUserDetails();
        Long userId = userDetails.getUserId();

        if (Objects.isNull(soHeader1))
            throw new CommonException("----------此订单不存在----------");
        if(!userId.equals(soHeader1.getCreatedBy()))
            throw new CommonException("----------当前用户与单据创建人不一致----------");
        if (!(soHeader1.getOrderStatus().equals("NEW") || soHeader1.getOrderStatus().equals("REJECTED")))
            throw new CommonException("----------订单状态非新建或非已拒绝,无法删除----------");

        soLineRepository.batchDelete(soLineRepository.select(SoLine.FIELD_SO_HEADER_ID,soHeader1.getSoHeaderId()));
        soHeaderRepository.deleteByPrimaryKey(soHeader1);
    }

    @Override
    public SoHeader createHL(Long organizationId, SoHeader soHeader) {
        validObject(soHeader);
        //获取当前用户id
        CustomUserDetails userDetails = DetailsHelper.getUserDetails();
        Long userId = userDetails.getUserId();
        SoHeader soHeader1 = soHeaderRepository.selectByPrimaryKey(soHeader.getSoHeaderId());
        //          头表创建/更新
        if(Objects.isNull(soHeader1)) {
            Calendar c = Calendar.getInstance();
            c.setTime(soHeader.getOrderDate());
            if (c.get(Calendar.YEAR)<=2019)
                throw new CommonException("----------订单日期未大于2019年----------");
            soHeaderRepository.insertSelective(soHeader);
        }
        else{
            if (!(soHeader1.getOrderStatus().equals("NEW") || soHeader1.getOrderStatus().equals("REJECTED")))
                throw new CommonException("----------订单状态非新建或非已拒绝,无法创建----------");
            if(!userId.equals(soHeader1.getCreatedBy()))
                throw new CommonException("----------当前用户与单据创建人不一致----------");
            soHeaderRepository.updateByPrimaryKeySelective(soHeader);
        }

        //          行表创建/更新
        for(SoLine soLine : soHeader.getSoLineList()) {
            SoLine soLine1 = soLineRepository.selectByPrimaryKey(soLine.getSoLineId());
            if (Objects.isNull(soLine1)) {
                soLineRepository.insertSelective(soLine);
            } else {
                if (!(soHeader1.getOrderStatus().equals("NEW") || soHeader1.getOrderStatus().equals("REJECTED")))
                    throw new CommonException("----------订单状态非新建或非已拒绝,无法创建----------");
                if (!userId.equals(soHeader1.getCreatedBy()))
                    throw new CommonException("----------当前用户与单据创建人不一致----------");
                soLineRepository.updateByPrimaryKeySelective(soLine);
            }
        }
        return soHeader;
    }

    @Override
    public void stateChange() {
        List<SoHeader> soHeaderList=soHeaderRepository.select(SoHeader.FIELD_ORDER_STATUS,"APPROVED");
        for(SoHeader soHeader: soHeaderList){
            soHeader.setOrderStatus("CLOSED");
        }
        soHeaderRepository.batchUpdateByPrimaryKeySelective(soHeaderList);
    }
}
