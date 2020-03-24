package com.echo.blog.dao;

import com.echo.blog.po.ProductPo;

public interface ProductDao {

    int deleteByPrimaryKey(Long id);

    int insert(ProductPo record);

    int insertSelective(ProductPo record);

    ProductPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPo record);

    int updateByPrimaryKey(ProductPo record);
}