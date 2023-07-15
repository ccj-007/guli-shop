package com.chen.gulimall.product.service.impl;

import com.chen.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.common.utils.PageUtils;
import com.chen.common.utils.Query;

import com.chen.gulimall.product.dao.BrandDao;
import com.chen.gulimall.product.entity.BrandEntity;
import com.chen.gulimall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // 1. key的模糊查询
        QueryWrapper<BrandEntity> queryWarpper = new QueryWrapper<>();
        Object key = params.get("key");

        if(!StringUtils.isEmpty(key)) {
            queryWarpper.eq("brand_id", key).or().like("name", key);
        }

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWarpper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void updateCascade(BrandEntity brand) {
        this.updateById(brand);

        // 同步更新其他关联表的数据
        if (!StringUtils.isEmpty(brand.getName())) {
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());
        }
        // TODO 更新其他关联
    }
}