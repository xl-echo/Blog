package com.echo.blog.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * article
 *
 * @author echo
 * @date 2020-01-19
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 自增主键
     */
    private Long id;

    /**
     * blog标题
     */
    private String title;

    /**
     * blog点击量
     */
    private Long clicks;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 作者
     */
    private String author;

    /**
     * 是否是原创 0:原创 1:转载 2:翻译
     */
    private Byte ifOriginal;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * blog内容
     */
    private String content;

}