package com.hand.demo.app.service.impl;

import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import com.hand.demo.app.service.ItemService;
import com.hand.demo.domain.entity.Item;
import com.hand.demo.domain.repository.ItemRepository;
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
public class ItemServiceImpl extends BaseAppService implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<Item> list(Long tenantId, Item item, PageRequest pageRequest) {
        return itemRepository.pageAndSort(pageRequest, item);
    }

    @Override
    public Item detail(Long tenantId, Long itemId) {
        return itemRepository.selectByPrimaryKey(itemId);
    }

    @Override
    public Item create(Long tenantId, Item item) {
        validObject(item);
            itemRepository.insertSelective(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Item update(Long tenantId, Item item) {
        SecurityTokenHelper.validToken(item);
            itemRepository.updateByPrimaryKeySelective(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Item item) {
        SecurityTokenHelper.validToken(item);
            itemRepository.deleteByPrimaryKey(item);
    }
}
