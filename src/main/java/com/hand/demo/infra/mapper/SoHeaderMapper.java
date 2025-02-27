package com.hand.demo.infra.mapper;

import com.hand.demo.domain.entity.SoHeader;
import io.choerodon.mybatis.common.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Order;
import java.util.List;

/**
 * Mapper
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Component
public interface SoHeaderMapper extends BaseMapper<SoHeader> {

    List<SoHeader> query(@Param("organizationId") Long organizationId, @Param("soHeader") SoHeader soHeader);
}
