package com.echo.blog.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * product
 * @author echo
 */
@Data
public class ProductPo implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品介绍
     */
    private String productDesc;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;

    /**
     * 产品图片
     */
    private String productImg;

    /**
     * 产品数量
     */
    private Long productNumber;

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