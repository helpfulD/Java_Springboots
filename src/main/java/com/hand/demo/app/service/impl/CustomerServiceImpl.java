package com.hand.demo.app.service.impl;

import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import com.hand.demo.app.service.CustomerService;
import com.hand.demo.domain.entity.Customer;
import com.hand.demo.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务默认实现
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Service
public class CustomerServiceImpl extends BaseAppService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> list(Long tenantId, Customer customer, PageRequest pageRequest) {
        return customerRepository.pageAndSort(pageRequest, customer);
    }

    @Override
    public Customer detail(Long tenantId, Long customerId) {
        return customerRepository.selectByPrimaryKey(customerId);
    }

    @Override
    public Customer create(Long tenantId, Customer customer) {
        validObject(customer);
            customerRepository.insertSelective(customer);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer update(Long tenantId, Customer customer) {
        SecurityTokenHelper.validToken(customer);
            customerRepository.updateByPrimaryKeySelective(customer);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Customer customer) {
        SecurityTokenHelper.validToken(customer);
            customerRepository.deleteByPrimaryKey(customer);
    }
}
