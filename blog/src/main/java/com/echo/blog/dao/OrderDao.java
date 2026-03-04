package com.echo.blog.dao;

import com.echo.blog.dto.OrderDto;
import com.echo.blog.po.OrderPo;

import java.util.List;
import java.util.Map;

/**
 * @author echo
 */
public interface OrderDao {

    int deleteByPrimaryKey(Long id);

    int insert(OrderPo orderPo);

    int insertSelective(OrderPo orderPo);

    OrderPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPo orderPo);

    int updateByPrimaryKey(OrderPo orderPo);

    List<OrderPo> getList(Map<String, Integer> map);

    Integer getTotal(Map<String, Integer> map);

    /**
     * 查询所有订单
     */
    List<OrderPo> selectAll();

    /**
     * 根据用户ID查询订单
     */
    List<OrderPo> selectByUserId(Long userId);

    /**
     * 分页查询订单
     */
    OrderDto selectByPage(Integer page, Integer size);
}


