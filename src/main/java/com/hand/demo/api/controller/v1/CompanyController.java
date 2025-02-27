package com.hand.demo.api.controller.v1;

import com.hand.demo.config.SwaggerTags;
import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import com.hand.demo.app.service.CompanyService;
import com.hand.demo.domain.entity.Company;
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
@Api(tags = SwaggerTags.COMPANY)
@RestController("companyController.v1")
@RequestMapping("/v1/{organizationId}/companys")
public class CompanyController extends BaseController {
    
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Company>> list(@PathVariable("organizationId") Long organizationId, Company company, @ApiIgnore @SortDefault(value = Company.FIELD_COMPANY_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Company> list = companyService.list(organizationId, company, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long companyId) {
        Company company = companyService.detail(organizationId, companyId);
        return Results.success(company);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Company> create(@PathVariable("organizationId") Long organizationId, @RequestBody Company company) {
        companyService.create(organizationId, company);
        return Results.success(company);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Company> update(@PathVariable("organizationId") Long organizationId, @RequestBody Company company) {
        companyService.update(organizationId, company);
        return Results.success(company);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Company company) {
        companyService.remove(company);
        return Results.success();
    }

}
