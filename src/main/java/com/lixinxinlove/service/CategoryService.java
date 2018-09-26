package com.lixinxinlove.service;

import com.lixinxinlove.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIds);

    ProductCategory save(ProductCategory productCategory);

    void delete(ProductCategory productCategory);

}
