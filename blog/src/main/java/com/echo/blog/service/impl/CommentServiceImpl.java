package com.echo.blog.service.impl;

import com.echo.blog.dao.CommentDao;
import com.echo.blog.po.CommentPo;
import com.echo.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 评论服务实现
 * @author echo
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    /**
     * 根据ID获取评论
     */
    @Override
    public CommentPo getCommentById(Long id) {
        return commentDao.selectById(id);
    }

    /**
     * 获取所有评论
     */
    @Override
    public List<CommentPo> getAllComments() {
        return commentDao.selectAll();
    }

    /**
     * 按文章ID获取评论
     */
    @Override
    public List<CommentPo> getCommentsByArticleId(Long articleId) {
        return commentDao.selectByArticleId(articleId);
    }

    /**
     * 创建评论
     */
    @Override
    public CommentPo createComment(CommentPo comment) {
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        commentDao.insert(comment);
        return comment;
    }

    /**
     * 更新评论
     */
    @Override
    public CommentPo updateComment(CommentPo comment) {
        comment.setUpdateTime(new Date());
        commentDao.updateById(comment);
        return comment;
    }

    /**
     * 删除评论
     */
    @Override
    public void deleteComment(Long id) {
        commentDao.deleteById(id);
    }

    /**
     * 清空所有评论缓存
     */
    public void clearAll$1Cache() { }

    /**
     * 添加评论（兼容Controller调用）
     */
    @Override
    public String addComment(CommentPo comment) {
        createComment(comment);
        return "评论成功";
    }

    /**
     * 删除评论（兼容Controller调用）
     */
    @Override
    public String deleteCommentById(String id) {
        try {
            Long commentId = Long.parseLong(id);
            deleteComment(commentId);
            return "删除成功";
        } catch (NumberFormatException e) {
            return "删除失败，ID格式错误";
        }
    }
}





