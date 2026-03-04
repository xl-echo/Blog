package com.echo.blog.service;

import com.echo.blog.po.ProductPo;

import java.util.List;

/**
 * 产品服务接口
 * @author echo
 */
public interface ProductService {

    /**
     * 根据ID获取产品
     * @param id 产品ID
     * @return 产品信息
     */
    ProductPo getProductById(Long id);

    /**
     * 获取所有产品
     * @return 产品列表
     */
    List<ProductPo> getAllProducts();

    /**
     * 创建产品
     * @param product 产品信息
     * @return 创建的产品
     */
    ProductPo createProduct(ProductPo product);

    /**
     * 更新产品
     * @param product 产品信息
     * @return 更新后的产品
     */
    ProductPo updateProduct(ProductPo product);

    /**
     * 删除产品
     * @param id 产品ID
     */
    void deleteProduct(Long id);

    /**
     * 按分类获取产品
     * @param categoryId 分类ID
     * @return 产品列表
     */
    List<ProductPo> getProductsByCategory(Long categoryId);

    /**
     * 按关键词搜索产品
     * @param keyword 关键词
     * @return 产品列表
     */
    List<ProductPo> searchProducts(String keyword);

    /**
     * 获取产品信息
     * @return 产品信息
     */
    ProductPo getProduct();

    /**
     * 获取产品库存
     * @return 产品库存
     */
    String getProductNumber();
}



