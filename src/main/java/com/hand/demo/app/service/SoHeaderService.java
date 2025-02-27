package com.hand.demo.app.service;

import com.hand.demo.domain.entity.SoHeader;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.springframework.validation.BindingResult;

/**
 * 应用服务
 *
 * @author hpf 2021-07-19 10:31:46
 */
public interface SoHeaderService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param soHeader 头对象
     * @param pageRequest 分页
     * @return 列表
     */
    Page<SoHeader> list(Long tenantId, SoHeader soHeader, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param soHeaderId 主键
     * @return 列表
     */
        SoHeader detail(Long tenantId, Long soHeaderId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param soHeader 头对象
     * @return 创建的头对象
     */
        SoHeader create(Long tenantId, SoHeader soHeader);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param soHeader 头对象
     * @return 更新的头对象
     */
        SoHeader update(Long tenantId, SoHeader soHeader);

    /**
     * 删除
     *
     * @param soHeader 头对象
     */
    void remove(SoHeader soHeader);

    /**
     *
     * @param organizationId    orgId
     * @param soHeader  头对象
     * @param pageRequest   分页
     * @return 查询结果
     */
    Page<SoHeader> query(Long organizationId, SoHeader soHeader, PageRequest pageRequest);

    /**
     *
     * @param organizationId    orgId
     * @param soHeader  头对象
     */
    void stateControl(Long organizationId, SoHeader soHeader);

    /**
     *
     * @param soHeader 头对象
     */
    void removeById(SoHeader soHeader);

    /**
     *
     * @param organizationId    orgId
     * @param soHeader  头对象
     */
    SoHeader createHL(Long organizationId, SoHeader soHeader);

    /**
     *
     */
    void stateChange();
}
