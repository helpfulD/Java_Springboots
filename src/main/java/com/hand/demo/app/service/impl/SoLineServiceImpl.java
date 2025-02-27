package com.hand.demo.app.service.impl;

import com.hand.demo.domain.entity.SoHeader;
import com.hand.demo.domain.repository.SoHeaderRepository;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.CustomUserDetails;
import io.choerodon.core.oauth.DetailsHelper;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import com.hand.demo.app.service.SoLineService;
import com.hand.demo.domain.entity.SoLine;
import com.hand.demo.domain.repository.SoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.Objects;

/**
 * 应用服务默认实现
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Service
public class SoLineServiceImpl extends BaseAppService implements SoLineService {

    private final SoLineRepository soLineRepository;
    private final SoHeaderRepository soHeaderRepository;

    @Autowired
    public SoLineServiceImpl(SoLineRepository soLineRepository, SoHeaderRepository soHeaderRepository) {
        this.soLineRepository = soLineRepository;
        this.soHeaderRepository = soHeaderRepository;
    }

    @Override
    public Page<SoLine> list(Long tenantId, SoLine soLine, PageRequest pageRequest) {
        return soLineRepository.pageAndSort(pageRequest, soLine);
    }

    @Override
    public SoLine detail(Long tenantId, Long soLineId) {
        return soLineRepository.selectByPrimaryKey(soLineId);
    }

    @Override
    public SoLine create(Long tenantId, SoLine soLine) {
        validObject(soLine);
            soLineRepository.insertSelective(soLine);
        return soLine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoLine update(Long tenantId, SoLine soLine) {
        SecurityTokenHelper.validToken(soLine);
            soLineRepository.updateByPrimaryKeySelective(soLine);
        return soLine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(SoLine soLine) {
        SecurityTokenHelper.validToken(soLine);
            soLineRepository.deleteByPrimaryKey(soLine);
    }

    @Override
    public Page<SoLine> query(Long organizationId, PageRequest pageRequest,Long soHeaderId) {

        return soLineRepository.pageAndSort(pageRequest,SoLine.FIELD_SO_HEADER_ID,soHeaderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long soLineId) {

        SoLine soLine=soLineRepository.selectByPrimaryKey(soLineId);
        //获取当前用户id
        CustomUserDetails userDetails = DetailsHelper.getUserDetails();
        Long userId = userDetails.getUserId();

        if (Objects.isNull(soLine))
            throw new CommonException("----------此订单不存在----------");
        if(!userId.equals(soLine.getCreatedBy()))
            throw new CommonException("----------当前用户与单据创建人不一致----------");
        SoHeader soHeader=soHeaderRepository.selectByPrimaryKey(soLine.getSoHeaderId());
        if (!(soHeader.getOrderStatus().equals("NEW") || soHeader.getOrderStatus().equals("REJECTED")))
            throw new CommonException("----------订单状态非新建或非已拒绝,无法删除----------");
        soLineRepository.deleteByPrimaryKey(soLineId);
    }
}



