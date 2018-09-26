package com.lixinxinlove.controller;


import com.lixinxinlove.VO.ProductInfoVO;
import com.lixinxinlove.VO.ProductVO;
import com.lixinxinlove.VO.ResultVO;
import com.lixinxinlove.dataobject.ProductCategory;
import com.lixinxinlove.dataobject.ProductInfo;
import com.lixinxinlove.service.CategoryService;
import com.lixinxinlove.service.ProductService;
import com.lixinxinlove.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */

@RestController
@RequestMapping("buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list1")
    public ResultVO test() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        ProductVO productVO = new ProductVO();
        productVO.setCategoryName("电器");
        productVO.setCategoryType(1);
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productInfoVO.setProductName("电脑");
        productInfoVO.setProductId("121231231231");
        productInfoVO.setProductPrice(new BigDecimal(5000.99));
        productInfoVO.setProductIcon("http://lixinxinlove/icon.jpg");
        productInfoVO.setProductDescription("很好用");
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        resultVO.setData(Arrays.asList(productVO));
        return resultVO;
    }


    @GetMapping("/list")
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();

        //1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();


        System.out.print(productInfoList.size()+"------------------------");

        //2. 查询类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList
                .stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        //3.商品类型
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);


        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {

            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        return ResultVOUtil.success(productVOList);
    }
}
