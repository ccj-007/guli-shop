package com.chen.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.gulimall.product.entity.ProductAttrValueEntity;
import com.chen.gulimall.product.vo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * spu属性值
 * 
 * @author 夏沫止水
 * @email HeJieLin@gulimall.com
 * @date 2020-05-22 19:00:18
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {


}
