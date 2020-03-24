package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.po.OrderPo;
import com.echo.blog.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author echo
 * @date 2020/2/15 19:07
 **/
@RestController
@RequestMapping("/orderController")
public class CyclicBarrierController {

    /**
     * 并发线程数量，Windows机器不要设置过大
     */
    private static final int THREAD_NUM = 1000;

    @Autowired
    private OrderService orderService;

    /**
     * 并发测试
     *
     * @return Result<String>
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<String> add(@RequestBody OrderPo orderPo) {
        Result<String> result = new Result<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            executorService.execute(new CyclicBarrierController().new MyThread(orderService, orderPo, cyclicBarrier));
        }
        executorService.shutdown();
        Result.packageResultMethod(result, "success");
        return result;
    }

    public class MyThread implements Runnable {
        private OrderPo orderPo;
        private OrderService orderService;
        private CyclicBarrier cyclicBarrier;

        public MyThread(OrderService orderService, OrderPo orderPo, CyclicBarrier cyclicBarrier) {
            this.orderPo = orderPo;
            this.orderService = orderService;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            orderService.save(orderPo);
        }

    }

}
