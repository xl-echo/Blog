package com.echo.blog.po;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论实体类
 * @author echo
 */
@Data
public class CommentPo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    private Long id;
    
    /**
     * 文章id
     */
    private Long articleId;
    
    /**
     * 评论者id
     */
    private Long userId;
    
    /**
     * 评论者用户名
     */
    private String username;
    
    /**
     * 评论内容
     */
    private String content;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
}


