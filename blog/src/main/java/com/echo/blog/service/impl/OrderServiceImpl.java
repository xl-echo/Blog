package com.echo.blog.service.impl;

import com.echo.blog.dao.OrderDao;
import com.echo.blog.po.OrderPo;
import com.echo.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单服务实现类
 * 使用Spring Cache替代Redis
 * @author echo
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    /**
     * 根据ID获取订单
     * 使用Spring Cache缓存
     * @param id 订单ID
     * @return 订单信息
     */
    @Override
    @Cacheable(value = "orders", key = "#id", unless = "#result == null")
    public OrderPo getOrderById(Long id) {
        return orderDao.selectByPrimaryKey(id);
    }

    /**
     * 获取所有订单
     * 使用Spring Cache缓存
     * @return 订单列表
     */
    @Override
    @Cacheable(value = "orders", key = "'all'")
    public List<OrderPo> getAllOrders() {
        return orderDao.selectAll();
    }

    /**
     * 创建订单
     * @param order 订单信息
     * @return 创建的订单
     */
    @Override
    @CachePut(value = "orders", key = "#result.id")
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
    @CachePut(value = "orders", key = "#order.id")
    public OrderPo updateOrder(OrderPo order) {
        orderDao.updateByPrimaryKey(order);
        return order;
    }

    /**
     * 删除订单
     * @param id 订单ID
     */
    @Override
    @CacheEvict(value = "orders", key = "#id")
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
    @Cacheable(value = "orders", key = "'user:' + #userId")
    public List<OrderPo> getOrdersByUserId(Long userId) {
        return orderDao.selectByUserId(userId);
    }

    /**
     * 清空所有订单缓存
     */
    @CacheEvict(value = "orders", allEntries = true)
    public void clearAllOrderCache() {
        // 清空缓存
    }
}
