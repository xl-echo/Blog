package com.echo.blog.po;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 点赞实体类
 * @author echo
 */
@Data
public class LikePo implements Serializable {
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
     * 点赞者id
     */
    private Long userId;
    
    /**
     * 点赞者用户名
     */
    private String username;
    
    /**
     * 创建时间
     */
    private Date createTime;
}
