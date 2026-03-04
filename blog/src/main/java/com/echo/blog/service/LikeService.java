package com.echo.blog.service;

import com.echo.blog.po.LikePo;

/**
 * 点赞服务接口
 * @author echo
 */
public interface LikeService {

    /**
     * 点赞文章
     */
    String likeArticle(Long articleId, Long userId, String username);

    /**
     * 取消点赞
     */
    String unlikeArticle(Long articleId, Long userId);

    /**
     * 检查是否已点赞
     */
    boolean isLiked(Long articleId, Long userId);

    /**
     * 获取点赞数
     */
    int getLikeCount(Long articleId);
}



