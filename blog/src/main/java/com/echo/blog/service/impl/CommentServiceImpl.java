package com.echo.blog.service.impl;

import com.echo.blog.dao.CommentDao;
import com.echo.blog.po.CommentPo;
import com.echo.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 评论服务实现
 * @author echo
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public String addComment(CommentPo comment) {
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        commentDao.insert(comment);
        return "评论成功";
    }

    @Override
    public List<CommentPo> getCommentsByArticleId(Long articleId) {
        return commentDao.selectByArticleId(articleId);
    }

    @Override
    public String deleteComment(Long id) {
        commentDao.deleteById(id);
        return "删除成功";
    }
}
