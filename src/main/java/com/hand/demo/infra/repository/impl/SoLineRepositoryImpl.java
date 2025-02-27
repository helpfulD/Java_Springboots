package com.hand.demo.infra.repository.impl;

import com.hand.demo.infra.mapper.SoHeaderMapper;
import com.hand.demo.infra.mapper.SoLineMapper;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import com.hand.demo.domain.entity.SoLine;
import com.hand.demo.domain.repository.SoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Component
public class SoLineRepositoryImpl extends BaseRepositoryImpl<SoLine> implements SoLineRepository {

}
