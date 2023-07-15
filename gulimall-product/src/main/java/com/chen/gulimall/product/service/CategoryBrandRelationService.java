package com.chen.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.common.utils.PageUtils;
import com.chen.gulimall.product.entity.BrandEntity;
import com.chen.gulimall.product.entity.CategoryBrandRelationEntity;
import com.chen.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author ccj
 * @email sunlightcs@gmail.com
 * @date 2023-06-04 13:12:35
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    List<BrandEntity> getBrandsByCatId(Long catId);

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrand(Long brandId, String name);

    void updateCategory(CategoryEntity category);
}

