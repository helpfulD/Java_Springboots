package com.hand.demo.api.controller.v1;

import com.hand.demo.config.SwaggerTags;
import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import com.hand.demo.app.service.SoHeaderService;
import com.hand.demo.domain.entity.SoHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 管理 API
 *
 * @author hpf 2021-07-19 10:31:46
 */
@Api(tags = SwaggerTags.SOHEADER)
@RestController("soHeaderController.v1")
@RequestMapping("/v1/{organizationId}/so-headers")
public class SoHeaderController extends BaseController {

    private final SoHeaderService soHeaderService;

    @Autowired
    public SoHeaderController(SoHeaderService soHeaderService) {
        this.soHeaderService = soHeaderService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<SoHeader>> list(@PathVariable("organizationId") Long organizationId, SoHeader soHeader, @ApiIgnore @SortDefault(value = SoHeader.FIELD_SO_HEADER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<SoHeader> list = soHeaderService.list(organizationId, soHeader, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soHeaderId}")
    public ResponseEntity<SoHeader> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long soHeaderId) {
        SoHeader soHeader = soHeaderService.detail(organizationId, soHeaderId);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<SoHeader> create(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.create(organizationId, soHeader);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<SoHeader> update(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.update(organizationId, soHeader);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.remove(soHeader);
        return Results.success();
    }

    @ApiOperation(value = "销售订单汇总查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/query")
    public ResponseEntity<Page<SoHeader>> query(@PathVariable("organizationId") Long organizationId, SoHeader soHeader, @ApiIgnore @SortDefault(value = SoHeader.FIELD_SO_HEADER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {

        Page<SoHeader> list = soHeaderService.query(organizationId, soHeader, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "订单状态控制")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/stateControl")
    public ResponseEntity<SoHeader> stateControl(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.stateControl(organizationId, soHeader);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "订单删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/deleteById")
    public ResponseEntity<?> removeById(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.removeById(soHeader);
        return Results.success();
    }

    @ApiOperation(value = "订单保存")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/CreateHL")
    public ResponseEntity<SoHeader> createHL(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.createHL(organizationId, soHeader);
        return Results.success(soHeader);
    }

}
