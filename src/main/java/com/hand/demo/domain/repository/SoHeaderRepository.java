package com.hand.demo.domain.repository;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;

import com.hand.demo.domain.entity.SoHeader;

/**
 * 资源库
 *
 * @author hpf 2021-07-19 10:31:46
 */
public interface SoHeaderRepository extends BaseRepository<SoHeader> {

    Page<SoHeader> query(Long organizationId, SoHeader soHeader, PageRequest pageRequest);
}
