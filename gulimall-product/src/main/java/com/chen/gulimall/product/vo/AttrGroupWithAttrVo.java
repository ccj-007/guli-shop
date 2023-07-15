package com.chen.gulimall.product.vo;

import com.chen.gulimall.product.entity.AttrEntity;
import com.chen.gulimall.product.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrVo extends AttrGroupEntity {
    private List<AttrEntity> attrs;
}
