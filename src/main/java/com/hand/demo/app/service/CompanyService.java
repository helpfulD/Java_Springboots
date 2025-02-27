package com.hand.demo.app.service;

import com.hand.demo.domain.entity.Company;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务
 *
 * @author hpf 2021-07-19 10:31:46
 */
public interface CompanyService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param company 
     * @param pageRequest 分页
     * @return 列表
     */
    Page<Company> list(Long tenantId, Company company, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param companyId 主键
     * @return 列表
     */
        Company detail(Long tenantId, Long companyId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param company 
     * @return 
     */
        Company create(Long tenantId, Company company);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param company 
     * @return 
     */
        Company update(Long tenantId, Company company);

    /**
     * 删除
     *
     * @param company 
     */
    void remove(Company company);
}
