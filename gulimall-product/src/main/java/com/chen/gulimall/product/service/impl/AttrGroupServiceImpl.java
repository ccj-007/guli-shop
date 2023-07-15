package com.chen.gulimall.product.service.impl;

import com.chen.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.chen.gulimall.product.dao.AttrDao;
import com.chen.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.chen.gulimall.product.entity.AttrEntity;
import com.chen.gulimall.product.service.AttrService;
import com.chen.gulimall.product.vo.AttrGroupWithAttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.common.utils.PageUtils;
import com.chen.common.utils.Query;

import com.chen.gulimall.product.dao.AttrGroupDao;
import com.chen.gulimall.product.entity.AttrGroupEntity;
import com.chen.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    public AttrDao attrDao;

    @Autowired
    public AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    public AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        if(catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), new QueryWrapper<AttrGroupEntity>());
            return new PageUtils(page);
        } else {
            // select * from pms_attrgroup where catelog_id=? and (attr_group_id=key or attr_group_name like %key%)
            Object key = params.get("key");
            QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId);

            if (!StringUtils.isEmpty(key)) {
                wrapper.and((obj) -> {
                    obj.eq("attr_group_id", key).or().like("attr_group_name", key);
                });
            }
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        }
    }

    @Override
    public List<AttrEntity> getGroupAllAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> entities = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));

//        List<AttrEntity> AttrList = new ArrayList<AttrEntity>();
//        entities.stream().forEach(item -> {
//            Long attrId = item.getAttrId();
//            AttrEntity attrItem = attrDao.selectOne(new QueryWrapper<AttrEntity>().eq("attr_id", attrId));
//            AttrList.add(attrItem);
//        });

        List<Long> attrIds = entities.stream().map(attr -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        List<AttrEntity> attrGroupEntities = attrService.listByIds(attrIds);
        return attrGroupEntities;
    }

    /**
     * 根据分类id查出所有的分组及属性
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrVo> getGroupAllAttrsByCatelogId(Long catelogId) {
        List<AttrGroupEntity> groupList = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        List<AttrGroupWithAttrVo> collect = groupList.stream().map(group -> {
            AttrGroupWithAttrVo attrVo = new AttrGroupWithAttrVo();
            BeanUtils.copyProperties(group, attrVo);
            List<AttrEntity> relationAttr = attrService.getRelationAttr(attrVo.getAttrGroupId());

            attrVo.setAttrs(relationAttr);
            return  attrVo;
        }).collect(Collectors.toList());

        return collect;
    }
}