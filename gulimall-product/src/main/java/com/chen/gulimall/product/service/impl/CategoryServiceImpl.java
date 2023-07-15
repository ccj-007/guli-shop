package com.chen.gulimall.product.service.impl;

import com.chen.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.common.utils.PageUtils;
import com.chen.common.utils.Query;

import com.chen.gulimall.product.dao.CategoryDao;
import com.chen.gulimall.product.entity.CategoryEntity;
import com.chen.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1. 查出所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        // 2. 组装父子的树形结构, 所有的一级分类
        List<CategoryEntity> level1Menus = categoryEntities.stream().filter((categoryEntity ->
             categoryEntity.getParentCid() == 0)).map((menu) -> {
            menu.setChildren(getChildrens(menu, categoryEntities));
            return menu;
        }).sorted((menu1, menu2) -> {
            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        // TODO 1. 检查当前删除菜单，是否被别的地方引用
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 找到catelog的完整路径
     * @param catelogId
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> sumPath = findChildPath(paths, catelogId);

        Collections.reverse(sumPath);
        return (Long[]) paths.toArray(new Long[0]);
    }

    /**
     * 级联更新所有数据
     * @param category
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        if (!StringUtils.isEmpty(category.getName())) {
            categoryBrandRelationService.updateCategory(category);
        }
    }

    private List<Long> findChildPath(List<Long> paths, Long catelogId) {
        paths.add(catelogId);
        // 先找到父id
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid() != 0) {
            findChildPath(paths, byId.getParentCid());
        }
        return paths;
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter((categoryEntity ->
                categoryEntity.getParentCid() == root.getCatId())).map((menu) -> {
            menu.setChildren(getChildrens(menu, all));
            return menu;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() != null ? menu1.getSort() : 0) - (menu2.getSort() != null ? menu2.getSort() : 0);
        }).collect(Collectors.toList());

        return children;
    }
}