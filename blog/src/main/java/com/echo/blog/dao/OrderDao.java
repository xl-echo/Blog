package com.echo.blog.dao;

import com.echo.blog.po.OrderPo;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    int deleteByPrimaryKey(Long id);

    int insert(OrderPo orderPo);

    int insertSelective(OrderPo orderPo);

    OrderPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPo orderPo);

    int updateByPrimaryKey(OrderPo orderPo);

    List<OrderPo> getList(Map<String, Integer> map);

    Integer getTotal(Map<String, Integer> map);

}