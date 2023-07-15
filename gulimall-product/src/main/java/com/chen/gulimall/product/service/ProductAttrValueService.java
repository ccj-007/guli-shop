package com.chen.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.common.utils.PageUtils;
import com.chen.gulimall.product.entity.ProductAttrValueEntity;
import com.chen.gulimall.product.vo.SpuItemAttrGroupVo;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:36
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
