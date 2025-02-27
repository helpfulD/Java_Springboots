package com.hand.demo.api.controller.v1;

import com.hand.demo.config.SwaggerTags;
import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import com.hand.demo.app.service.SoLineService;
import com.hand.demo.domain.entity.SoLine;
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
@Api(tags = SwaggerTags.SOLINE)
@RestController("soLineController.v1")
@RequestMapping("/v1/{organizationId}/so-lines")
public class SoLineController extends BaseController {

    private final SoLineService soLineService;

    @Autowired
    public SoLineController(SoLineService soLineService) {
        this.soLineService = soLineService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<SoLine>> list(@PathVariable("organizationId") Long organizationId, SoLine soLine, @ApiIgnore @SortDefault(value = SoLine.FIELD_SO_LINE_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<SoLine> list = soLineService.list(organizationId, soLine, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soLineId}")
    public ResponseEntity<SoLine> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long soLineId) {
        SoLine soLine = soLineService.detail(organizationId, soLineId);
        return Results.success(soLine);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<SoLine> create(@PathVariable("organizationId") Long organizationId, @RequestBody SoLine soLine) {
        soLineService.create(organizationId, soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<SoLine> update(@PathVariable("organizationId") Long organizationId, @RequestBody SoLine soLine) {
        soLineService.update(organizationId, soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@PathVariable("organizationId") Long organizationId, @RequestBody SoLine soLine) {
        soLineService.remove(soLine);
        return Results.success();
    }

    @ApiOperation(value = "订单行查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/query/{soHeaderId}")
    public ResponseEntity<Page<SoLine>> query(@PathVariable("organizationId") Long organizationId, @PathVariable("soHeaderId") Long soHeaderId,
                                              @ApiIgnore @SortDefault(value = SoLine.FIELD_SO_LINE_ID,
                                                      direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<SoLine> list = soLineService.query(organizationId, pageRequest, soHeaderId);
        return Results.success(list);
    }

    @ApiOperation(value = "订单行删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/delete/{soLineId}")
    public ResponseEntity<?> removeById(@PathVariable("organizationId") Long organizationId, @PathVariable("soLineId") Long soLineId) {
        soLineService.removeById(soLineId);
        return Results.success();
    }
}
