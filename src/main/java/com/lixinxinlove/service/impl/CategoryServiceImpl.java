package com.lixinxinlove.service.impl;

import com.lixinxinlove.dataobject.ProductCategory;
import com.lixinxinlove.repository.ProductCategoryRepository;
import com.lixinxinlove.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIds) {
        return repository.findAllById(categoryIds);
    }

    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> categoryType) {
        return repository.findByCategoryType(categoryType);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public void delete(ProductCategory productCategory) {
        repository.delete(productCategory);
    }
}
