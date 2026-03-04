package com.echo.blog.service.impl;

import com.echo.blog.dao.OrderDao;
import com.echo.blog.dto.OrderDto;
import com.echo.blog.po.OrderPo;
import com.echo.blog.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单服务实现类
 * @author echo
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 根据ID获取订单
     * 使用Spring Cache缓存
     * @param id 订单ID
     * @return 订单信息
     */
    @Override
    public OrderPo getOrderById(Long id) {
        return orderDao.selectByPrimaryKey(id);
    }

    /**
     * 获取所有订单
     * 使用Spring Cache缓存
     * @return 订单列表
     */
    @Override
    public List<OrderPo> getAllOrders() {
        return orderDao.selectAll();
    }

    /**
     * 创建订单
     * @param order 订单信息
     * @return 创建的订单
     */
    @Override
    public OrderPo createOrder(OrderPo order) {
        orderDao.insert(order);
        return order;
    }

    /**
     * 更新订单
     * @param order 订单信息
     * @return 更新后的订单
     */
    @Override
    public OrderPo updateOrder(OrderPo order) {
        orderDao.updateByPrimaryKey(order);
        return order;
    }

    /**
     * 删除订单
     * @param id 订单ID
     */
    @Override
    public void deleteOrder(Long id) {
        orderDao.deleteByPrimaryKey(id);
    }

    /**
     * 按用户ID获取订单
     * 使用Spring Cache缓存
     * @param userId 用户ID
     * @return 订单列表
     */
    @Override
    public List<OrderPo> getOrdersByUserId(Long userId) {
        return orderDao.selectByUserId(userId);
    }

    /**
     * 清空所有订单缓存
     */
    public void clearAll$1Cache() { }

    /**
     * 保存订单（兼容Controller调用）
     */
    @Override
    public String save(OrderPo orderPo) {
        createOrder(orderPo);
        return "保存成功";
    }

    /**
     * 分页获取订单列表（兼容Controller调用）
     */
    @Override
    public OrderDto getList(Integer page, Integer size) {
        return orderDao.selectByPage(page, size);
    }
}





