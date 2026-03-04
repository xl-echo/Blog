package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.po.CommentPo;
import com.echo.blog.service.CommentService;
import com.echo.blog.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论控制器
 * @author echo
 */
@RestController
@RequestMapping("/commentController")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论（需要登录）
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<String> addComment(@RequestBody CommentPo comment,
                                      @RequestParam(value = "token") String token) {
        Result<String> result = new Result<>();

        // 验证token
        try {
            JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            result.setStatus(999);
            result.setMessage("请先登录后再评论");
            return result;
        }

        String response = commentService.addComment(comment);
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 获取文章评论列表（无需登录）
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<CommentPo>> getComments(@RequestParam(value = "articleId") Long articleId) {
        Result<List<CommentPo>> result = new Result<>();
        List<CommentPo> comments = commentService.getCommentsByArticleId(articleId);
        Result.packageResultMethod(result, comments);
        return result;
    }

    /**
     * 删除评论（需要登录）
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<String> deleteComment(@RequestParam(value = "id") String id,
                                        @RequestParam(value = "token") String token) {
        Result<String> result = new Result<>();

        // 验证token
        try {
            JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            result.setStatus(999);
            result.setMessage("请先登录后再删除评论");
            return result;
        }

        String response = commentService.deleteCommentById(id);
        Result.packageResultMethod(result, response);
        return result;
    }
}


