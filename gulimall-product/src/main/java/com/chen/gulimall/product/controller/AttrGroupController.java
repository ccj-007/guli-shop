package com.chen.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 
import com.chen.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.chen.gulimall.product.entity.AttrEntity;
import com.chen.gulimall.product.service.AttrAttrgroupRelationService;
import com.chen.gulimall.product.service.AttrService;
import com.chen.gulimall.product.service.CategoryService;
import com.chen.gulimall.product.vo.AttrGroupRelationVo;
import com.chen.gulimall.product.vo.AttrGroupWithAttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chen.gulimall.product.entity.AttrGroupEntity;
import com.chen.gulimall.product.service.AttrGroupService;
import com.chen.common.utils.PageUtils;
import com.chen.common.utils.R;



/**
 * 属性分组
 *
 * @author ccj
 * @email sunlightcs@gmail.com
 * @date 2023-06-04 13:12:35
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos) {
        attrAttrgroupRelationService.addRelation(vos);
        return R.ok();
    }

    /**
     * 获取分类下的所有属性和分组
     * /product/attrgroup/{catelogId}/withattr
     */
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWthAttrs(@PathVariable("catelogId") Long catelogId) {
        // 查出所有的分组，每个分组对应的属性list
        List<AttrGroupWithAttrVo> vos = attrGroupService.getGroupAllAttrsByCatelogId(catelogId
        );
        return R.ok().put("data", vos);
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    // //("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params,  catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    // //("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        // 新的查询返回[x, x, x]
        Long catelogId = attrGroup.getCatelogId();
        Long[] paths = categoryService.findCatelogPath(catelogId);

        // 存储到attrGroup
        attrGroup.setCatelogPath(paths);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // //("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // //("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // //("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return R.ok();
    }

    @GetMapping("{attrgroupId}/attr/relation")
    public R getGroupAllAttr(@PathVariable("attrgroupId") Long attrgroupId) {
        System.out.println("attrgroupId====" + attrgroupId);
        List<AttrEntity> list  = attrGroupService.getGroupAllAttr(attrgroupId);
        return R.ok().put("data", list);
    }

    @RequestMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos) {
        attrService.deleteRelation(vos);
        return R.ok();
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params){
        PageUtils page = attrService.getNoRelationAttr(attrgroupId,params);
        return R.ok().put("page", page);
    }
}
