package com.echo.blog.service;

import com.echo.blog.dto.OrderDto;
import com.echo.blog.po.OrderPo;

import java.util.List;

/**
 * 订单服务接口
 * @author echo
 */
public interface OrderService {

    /**
     * 根据ID获取订单
     * @param id 订单ID
     * @return 订单信息
     */
    OrderPo getOrderById(Long id);

    /**
     * 获取所有订单
     * @return 订单列表
     */
    List<OrderPo> getAllOrders();

    /**
     * 创建订单
     * @param order 订单信息
     * @return 创建的订单
     */
    OrderPo createOrder(OrderPo order);

    /**
     * 更新订单
     * @param order 订单信息
     * @return 更新后的订单
     */
    OrderPo updateOrder(OrderPo order);

    /**
     * 删除订单
     * @param id 订单ID
     */
    void deleteOrder(Long id);

    /**
     * 按用户ID获取订单
     * @param userId 用户ID
     * @return 订单列表
     */
    List<OrderPo> getOrdersByUserId(Long userId);

    /**
     * 保存订单
     * @param orderPo 订单信息
     * @return 保存结果
     */
    String save(OrderPo orderPo);

    /**
     * 分页获取订单列表
     * @param page 页码
     * @param size 每页大小
     * @return 订单列表
     */
    OrderDto getList(Integer page, Integer size);
}



