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
     * 根据ID查询评论
     */
    CommentPo selectById(@Param("id") Long id);

    /**
     * 查询所有评论
     */
    List<CommentPo> selectAll();

    /**
     * 根据文章id获取评论列表
     */
    List<CommentPo> selectByArticleId(@Param("articleId") Long articleId);

    /**
     * 更新评论
     */
    int updateById(CommentPo comment);

    /**
     * 删除评论
     */
    int deleteById(@Param("id") Long id);

    /**
     * 查询最新评论（限制数量）
     */
    List<CommentPo> selectLatest(@Param("limit") Integer limit);

    /**
     * 根据文章id查询评论数量
     */
    int countByArticleId(@Param("articleId") Long articleId);
}


