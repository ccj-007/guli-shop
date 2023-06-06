package com.chen.gulimall.product;

import com.chen.gulimall.product.entity.BrandEntity;
import com.chen.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimailProductApplicationTests {
    @Autowired
    BrandService brandService;
    
    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setDescript("");
        brandEntity.setName("华为");
        brandService.save(brandEntity);
    }
}
