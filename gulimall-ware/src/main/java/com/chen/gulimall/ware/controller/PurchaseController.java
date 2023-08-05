package com.chen.gulimall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.chen.gulimall.ware.vo.MergeVo;
import com.chen.gulimall.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chen.gulimall.ware.entity.PurchaseEntity;
import com.chen.gulimall.ware.service.PurchaseService;
import com.chen.common.utils.PageUtils;
import com.chen.common.utils.R;



/**
 * 采购信息
 *
 * @author chen
 * @email 596487930@qq.com
 * @date 2023-06-06 22:43:27
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 完成采购
     */
    @PostMapping("/done")
    //("ware:purchase:list")
    public R done(@RequestBody PurchaseDoneVo doneVo){
        purchaseService.done(doneVo);
        return R.ok();
    }


    /**
     * 合并采购单
     */
    @RequestMapping("/merge")
    //("ware:purchase:list")
    public R merge(@RequestBody MergeVo mergeVo){
        purchaseService.merge(mergeVo);

        return R.ok();
    }

    /**
     * 领取采购单
     */
    @PostMapping("/received")
    public R receive(@RequestBody List<Long> ids) {
        purchaseService.receive(ids);
        return R.ok();
    }

    /**
     * 未采购的列表
     */
    @RequestMapping("/unreceive/list")
    //("ware:purchase:list")
    public R unreceiveList(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryUnreceivePage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //("ware:purchase:info")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //("ware:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //("ware:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
