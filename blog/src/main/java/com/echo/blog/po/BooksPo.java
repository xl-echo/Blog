package com.echo.blog.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * books
 *
 * @author echo
 */
@Data
public class BooksPo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键自增id
     */
    private Long id;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍链接
     */
    private String bookImgLink;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}