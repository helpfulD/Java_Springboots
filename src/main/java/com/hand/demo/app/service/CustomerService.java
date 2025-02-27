package com.hand.demo.app.service;

import com.hand.demo.domain.entity.Customer;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务
 *
 * @author hpf 2021-07-19 10:31:46
 */
public interface CustomerService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param customer 
     * @param pageRequest 分页
     * @return 列表
     */
    Page<Customer> list(Long tenantId, Customer customer, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param customerId 主键
     * @return 列表
     */
        Customer detail(Long tenantId, Long customerId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param customer 
     * @return 
     */
        Customer create(Long tenantId, Customer customer);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param customer 
     * @return 
     */
        Customer update(Long tenantId, Customer customer);

    /**
     * 删除
     *
     * @param customer 
     */
    void remove(Customer customer);
}
