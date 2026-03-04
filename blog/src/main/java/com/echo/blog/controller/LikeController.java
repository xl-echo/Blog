package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.service.LikeService;
import com.echo.blog.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 点赞控制器
 * @author echo
 */
@RestController
@RequestMapping("/likeController")
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * 点赞文章（需要登录）
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Result<String> likeArticle(@RequestParam(value = "articleId") Long articleId,
                                       @RequestParam(value = "userId") Long userId,
                                       @RequestParam(value = "username") String username,
                                       @RequestParam(value = "token") String token) {
        Result<String> result = new Result<>();

        // 验证token
        try {
            JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            result.setStatus(999);
            result.setMessage("请先登录后再点赞");
            return result;
        }

        String response = likeService.likeArticle(articleId, userId, username);
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 取消点赞（需要登录）
     */
    @RequestMapping(value = "/unlike", method = RequestMethod.POST)
    public Result<String> unlikeArticle(@RequestParam(value = "articleId") Long articleId,
                                         @RequestParam(value = "userId") Long userId,
                                         @RequestParam(value = "token") String token) {
        Result<String> result = new Result<>();

        // 验证token
        try {
            JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            result.setStatus(999);
            result.setMessage("请先登录后再取消点赞");
            return result;
        }

        String response = likeService.unlikeArticle(articleId, userId);
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 检查是否已点赞（无需登录）
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<Boolean> checkLiked(@RequestParam(value = "articleId") Long articleId,
                                      @RequestParam(value = "userId") Long userId) {
        Result<Boolean> result = new Result<>();
        boolean isLiked = likeService.isLiked(articleId, userId);
        Result.packageResultMethod(result, isLiked);
        return result;
    }

    /**
     * 获取点赞数（无需登录）
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Result<Integer> getLikeCount(@RequestParam(value = "articleId") Long articleId) {
        Result<Integer> result = new Result<>();
        int count = likeService.getLikeCount(articleId);
        Result.packageResultMethod(result, count);
        return result;
    }
}


