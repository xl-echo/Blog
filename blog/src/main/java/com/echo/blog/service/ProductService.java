package com.echo.blog.service;

import com.echo.blog.po.ProductPo;

/**
 * @author echo
 * @date 2020/2/15 14:13
 **/
public interface ProductService {

    /**
     * 获取商品信息
     *
     * @return ProductPo
     */
    ProductPo getProduct();

    /**
     * 获取产品库存
     *
     * @return Long
     */
    String getProductNumber();

}
