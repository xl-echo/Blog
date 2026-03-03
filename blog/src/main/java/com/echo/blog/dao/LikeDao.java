package com.echo.blog.dao;

import com.echo.blog.po.LikePo;
import org.apache.ibatis.annotations.Param;

/**
 * 点赞DAO接口
 * @author echo
 */
public interface LikeDao {

    /**
     * 插入点赞记录
     */
    int insert(LikePo like);

    /**
     * 检查用户是否已点赞
     */
    LikePo selectByArticleIdAndUserId(@Param("articleId") Long articleId, 
                                      @Param("userId") Long userId);

    /**
     * 取消点赞
     */
    int deleteByArticleIdAndUserId(@Param("articleId") Long articleId, 
                                    @Param("userId") Long userId);

    /**
     * 获取文章点赞总数
     */
    int countByArticleId(@Param("articleId") Long articleId);
}
