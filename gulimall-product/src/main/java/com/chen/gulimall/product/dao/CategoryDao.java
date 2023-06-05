package com.chen.gulimall.product.dao;

import com.chen.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author ccj
 * @email sunlightcs@gmail.com
 * @date 2023-06-04 13:12:35
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
