package com.echo.blog.dao;

import com.echo.blog.po.CommentPo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 评论DAO接口
 * @author echo
 */
public interface CommentDao {

    /**
     * 插入评论
     */
    int insert(CommentPo comment);

    /**
     * 根据文章id获取评论列表
     */
    List<CommentPo> selectByArticleId(@Param("articleId") Long articleId);

    /**
     * 删除评论
     */
    int deleteById(@Param("id") Long id);
}
