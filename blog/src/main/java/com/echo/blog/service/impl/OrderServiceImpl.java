package com.echo.blog.service.impl;

import com.echo.blog.dao.OrderDao;
import com.echo.blog.dto.OrderDto;
import com.echo.blog.po.OrderPo;
import com.echo.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author echo
 * @date 2020/2/15 18:57
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private ReentrantLock lock = new ReentrantLock();

    private final RedisTemplate<String, String> redisTemplate;

    private final OrderDao orderDao;

    @Override
    public String save(OrderPo orderPo) {
        lock.lock();
        try {
            String productNumber = redisTemplate.opsForValue().get("productNumber");
            int i = Integer.parseInt(productNumber);
            if (i <= 0) {
                return "秒杀活动结束，已经被抢购空了";
            } else {
                redisTemplate.opsForValue().decrement("productNumber");
                orderPo.setCreateTime(new Date());
                orderPo.setUpdateTime(new Date());
                orderDao.insert(orderPo);
            }
        } finally {
            lock.unlock();
        }
        return "success";
    }

    @Override
    public OrderDto getList(Integer page, Integer size) {
        Map<String, Integer> map = new HashMap<>(16);
        map.put("page", (page - 1) * size);
        map.put("size", size);
        List<OrderPo> list = orderDao.getList(map);
        Integer total = orderDao.getTotal(map);
        OrderDto orderDto = new OrderDto();
        orderDto.setList(list);
        orderDto.setTotal(total);
        return orderDto;
    }

}
