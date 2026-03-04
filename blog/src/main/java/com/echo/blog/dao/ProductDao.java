package com.echo.blog.dao;

import com.echo.blog.po.ProductPo;

import java.util.List;

public interface ProductDao {

    int deleteByPrimaryKey(Long id);

    int insert(ProductPo record);

    int insertSelective(ProductPo record);

    ProductPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPo record);

    int updateByPrimaryKey(ProductPo record);

    /**
     * 查询所有产品
     */
    List<ProductPo> selectAll();

    /**
     * 根据分类ID查询产品
     */
    List<ProductPo> selectByCategoryId(Long categoryId);

    /**
     * 根据关键词搜索产品
     */
    List<ProductPo> searchByKeyword(String keyword);
}


