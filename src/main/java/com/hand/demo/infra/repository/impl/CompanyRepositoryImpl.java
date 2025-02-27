package com.hand.demo.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import com.hand.demo.domain.entity.Company;
import com.hand.demo.domain.repository.CompanyRepository;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Component
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company> implements CompanyRepository {


}
