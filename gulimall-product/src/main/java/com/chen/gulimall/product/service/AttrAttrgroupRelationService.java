package com.chen.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.common.utils.PageUtils;
import com.chen.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.chen.gulimall.product.vo.AttrGroupRelationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author ccj
 * @email sunlightcs@gmail.com
 * @date 2023-06-04 13:12:35
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addRelation(List<AttrGroupRelationVo> vos);
}

