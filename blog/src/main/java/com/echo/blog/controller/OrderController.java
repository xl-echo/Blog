package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.dto.OrderDto;
import com.echo.blog.po.OrderPo;
import com.echo.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author echo
 * @date 2020/2/15 18:54
 **/
@RestController
@RequestMapping("/orderController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    /**
     * 新增订单
     *
     * @param orderPo 订单信息
     * @return Result<String>
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<String> save(@RequestBody OrderPo orderPo) {
        Result<String> result = new Result<>();
        String string = orderService.save(orderPo);
        Result.packageResultMethod(result, string);
        return result;
    }

    /**
     * 新增订单
     *
     * @return Result<OrderDto>
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<OrderDto> getList(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Result<OrderDto> result = new Result<>();
        OrderDto orderDto = orderService.getList(page, size);
        Result.packageResultMethod(result, orderDto);
        return result;
    }

}
