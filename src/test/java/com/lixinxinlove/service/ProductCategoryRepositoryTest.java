package com.lixinxinlove.service;


import com.lixinxinlove.dataobject.ProductCategory;
import com.lixinxinlove.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Transient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    @Transactional
    public void findOneTest() {
        ProductCategory productCategory = repository.getOne(2);
        System.out.print(productCategory);
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("女生最爱", 5);
        ProductCategory result = repository.save(productCategory);
        System.out.print(result.toString());
//        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateTest() {
//        ProductCategory productCategory = repository.findOne(4);
//        productCategory.setCategoryName("男生最爱1");
        ProductCategory productCategory = new ProductCategory("男生最爱", 4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertEquals(productCategory, result);
    }
}
