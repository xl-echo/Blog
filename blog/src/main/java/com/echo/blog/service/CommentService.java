package com.echo.blog.service;

import com.echo.blog.po.CommentPo;
import java.util.List;

/**
 * 评论服务接口
 * @author echo
 */
public interface CommentService {

    /**
     * 添加评论
     */
    String addComment(CommentPo comment);

    /**
     * 获取文章评论列表
     */
    List<CommentPo> getCommentsByArticleId(Long articleId);

    /**
     * 删除评论
     */
    String deleteComment(Long id);
}
