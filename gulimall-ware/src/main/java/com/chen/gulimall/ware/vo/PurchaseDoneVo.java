package com.chen.gulimall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PurchaseDoneVo {
    public Object getItems;
    @NotNull
    private Long id;

    private List<PurchaseItemDoneVo> items;
}
