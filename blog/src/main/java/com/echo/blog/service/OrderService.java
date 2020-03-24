package com.echo.blog.service;

import com.echo.blog.dto.OrderDto;
import com.echo.blog.po.OrderPo;

/**
 * @author echo
 * @date 2020/2/15 18:56
 **/
public interface OrderService {

    /**
     * 新增订单
     *
     * @param orderPo 订单信息
     * @return String
     */
    String save(OrderPo orderPo);

    /**
     * 分页查询订单
     *
     * @param page 多少页
     * @param size 多少条
     * @return OrderDto
     */
    OrderDto getList(Integer page, Integer size);
}
