package com.hand.demo.api.controller.v1;

import com.hand.demo.config.SwaggerTags;
import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import com.hand.demo.app.service.ItemService;
import com.hand.demo.domain.entity.Item;
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
@Api(tags = SwaggerTags.ITEM)
@RestController("itemController.v1")
@RequestMapping("/v1/{organizationId}/items")
public class ItemController extends BaseController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Item>> list(@PathVariable("organizationId") Long organizationId, Item item, @ApiIgnore @SortDefault(value = Item.FIELD_ITEM_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Item> list = itemService.list(organizationId, item, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{itemId}")
    public ResponseEntity<Item> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long itemId) {
        Item item = itemService.detail(organizationId, itemId);
        return Results.success(item);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Item> create(@PathVariable("organizationId") Long organizationId, @RequestBody Item item) {
        itemService.create(organizationId, item);
        return Results.success(item);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Item> update(@PathVariable("organizationId") Long organizationId, @RequestBody Item item) {
        itemService.update(organizationId, item);
        return Results.success(item);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Item item) {
        itemService.remove(item);
        return Results.success();
    }

}
