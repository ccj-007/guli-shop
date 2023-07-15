package com.chen.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.common.utils.PageUtils;
import com.chen.gulimall.product.entity.AttrEntity;
import com.chen.gulimall.product.entity.AttrGroupEntity;
import com.chen.gulimall.product.vo.Attr;
import com.chen.gulimall.product.vo.AttrGroupWithAttrVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author ccj
 * @email sunlightcs@gmail.com
 * @date 2023-06-04 13:12:35
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrEntity> getGroupAllAttr(Long attrgroupId);

    List<AttrGroupWithAttrVo> getGroupAllAttrsByCatelogId(Long catelogId);
}

