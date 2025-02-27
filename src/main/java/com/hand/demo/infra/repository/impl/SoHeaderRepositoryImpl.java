package com.hand.demo.infra.repository.impl;

import com.hand.demo.infra.mapper.SoHeaderMapper;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import com.hand.demo.domain.entity.SoHeader;
import com.hand.demo.domain.repository.SoHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Component
public class SoHeaderRepositoryImpl extends BaseRepositoryImpl<SoHeader> implements SoHeaderRepository {

    @Autowired
    private SoHeaderMapper soHeaderMapper;

    @Override
    @ProcessLovValue
    public Page<SoHeader> query(Long organizationId, SoHeader soHeader, PageRequest pageRequest) {

        return PageHelper.doPage(pageRequest, () -> soHeaderMapper.query(organizationId, soHeader));
    }
}
