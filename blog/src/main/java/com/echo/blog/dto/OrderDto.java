package com.echo.blog.dto;

import com.echo.blog.po.OrderPo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author echo
 * @date 2020/2/18 15:34
 **/
@Data
public class OrderDto {

    private Integer total = 0;

    private List<OrderPo> list = new ArrayList<>(10);

}
