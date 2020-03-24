package com.echo.blog.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * order
 * @author echo
 */
@Data
@ToString
public class OrderPo implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}