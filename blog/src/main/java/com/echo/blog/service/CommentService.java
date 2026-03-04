package com.echo.blog.service;

import com.echo.blog.po.CommentPo;

import java.util.List;

/**
 * 评论服务接口
 * @author echo
 */
public interface CommentService {

    /**
     * 根据ID获取评论
     * @param id 评论ID
     * @return 评论信息
     */
    CommentPo getCommentById(Long id);

    /**
     * 获取所有评论
     * @return 评论列表
     */
    List<CommentPo> getAllComments();

    /**
     * 按文章ID获取评论
     * @param articleId 文章ID
     * @return 评论列表
     */
    List<CommentPo> getCommentsByArticleId(Long articleId);

    /**
     * 创建评论
     * @param comment 评论信息
     * @return 创建的评论
     */
    CommentPo createComment(CommentPo comment);

    /**
     * 更新评论
     * @param comment 评论信息
     * @return 更新后的评论
     */
    CommentPo updateComment(CommentPo comment);

    /**
     * 删除评论
     * @param id 评论ID
     */
    void deleteComment(Long id);

    /**
     * 添加评论（兼容Controller调用）
     * @param comment 评论信息
     * @return 操作结果
     */
    String addComment(CommentPo comment);

    /**
     * 删除评论（兼容Controller调用）
     * @param id 评论ID
     * @return 操作结果
     */
    String deleteCommentById(String id);
}



