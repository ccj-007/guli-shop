package com.chen.gulimall.product;

import com.chen.gulimall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimailProductApplicationTests {
    @Autowired
    BrandService brandService;

//    @Autowired
//    OSSClient ossClient;

    @Test
    public void testUpload() throws Exception {
//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\sky.webp");
//        ossClient.putObject("gulimall-ccjc", "sky.webp", fileInputStream);
//
//        ossClient.shutdown();
//
//        System.out.println("上传成功");
    }
}
