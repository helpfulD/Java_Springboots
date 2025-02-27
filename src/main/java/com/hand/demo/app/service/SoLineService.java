package com.hand.demo.app.service;

import com.hand.demo.domain.entity.SoLine;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务
 *
 * @author hpf 2021-07-19 10:31:46
 */
public interface SoLineService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param soLine 
     * @param pageRequest 分页
     * @return 列表
     */
    Page<SoLine> list(Long tenantId, SoLine soLine, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param soLineId 主键
     * @return 列表
     */
        SoLine detail(Long tenantId, Long soLineId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param soLine 
     * @return 
     */
        SoLine create(Long tenantId, SoLine soLine);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param soLine 
     * @return 
     */
        SoLine update(Long tenantId, SoLine soLine);

    /**
     * 删除
     *
     * @param soLine 
     */
    void remove(SoLine soLine);

    Page<SoLine> query(Long organizationId, PageRequest pageRequest,Long soHeaderId);

    void removeById(Long soLineId);
}
