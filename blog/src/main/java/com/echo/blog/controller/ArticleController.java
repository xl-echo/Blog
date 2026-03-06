package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.param.ArticleParam;
import com.echo.blog.po.ArticlePo;
import com.echo.blog.service.ArticleService;
import com.echo.blog.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author echo
 * @date 2020-01-19
 */
@RestController
@RequestMapping("/articleController")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 项目健康检查方法
     *
     * @return Result<String>
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result<String> test() {
        Result<String> result = new Result<>();
        String response = articleService.test();
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 保存文章（需要登录）
     *
     * @param articleParam 文章参数
     * @return Result<String>
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<String> save(@RequestBody ArticleParam articleParam) {
        Result<String> result = new Result<>();
        
        // 验证token
        String token = articleParam.getToken();
        try {
            JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            result.setStatus(999);
            result.setMessage("请先登录后再发布文章");
            return result;
        }
        
        String response = articleService.save(articleParam);
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 删除文章（需要登录）
     *
     * @param id 文章ID
     * @return Result<String>
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<String> delete(@RequestParam(value = "id") Long id, 
                                  @RequestParam(value = "token") String token) {
        Result<String> result = new Result<>();
        
        // 验证token
        try {
            JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            result.setStatus(999);
            result.setMessage("请先登录后再删除文章");
            return result;
        }
        
        String response = articleService.delete(id);
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 更新文章（需要登录）
     *
     * @param articlePo 文章对象
     * @return Result<String>
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<String> update(@RequestParam(value = "articlePo") ArticlePo articlePo,
                                  @RequestParam(value = "token") String token) {
        Result<String> result = new Result<>();
        
        // 验证token
        try {
            JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            result.setStatus(999);
            result.setMessage("请先登录后再更新文章");
            return result;
        }
        
        String response = articleService.update(articlePo);
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 分页获取文章摘要内容（无需登录）
     *
     * @param articleParam 查询条件
     * @return Result<List < ArticlePo>>
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<List<ArticlePo>> getList(ArticleParam articleParam) {
        Result<List<ArticlePo>> result = new Result<>();
        List<ArticlePo> articlePos = articleService.getList(articleParam);
        Result.packageResultMethod(result, articlePos);
        return result;
    }

    /**
     * 查询5条热门文章（无需登录）
     *
     * @return Result<List < ArticlePo>>
     */
    @RequestMapping(value = "/getHotList", method = RequestMethod.GET)
    public Result<List<ArticlePo>> getHotList() {
        Result<List<ArticlePo>> result = new Result<>();
        List<ArticlePo> articlePos = articleService.getHotList();
        Result.packageResultMethod(result, articlePos);
        return result;
    }

    /**
     * 根据文章id获取文章详情（无需登录）
     *
     * @param articleId 文章id
     * @return Result<ArticlePo>
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public Result<ArticlePo> getDetail(@RequestParam(value = "articleId") Integer articleId) {
        Result<ArticlePo> result = new Result<>();
        ArticlePo articlePo = articleService.getDetail(articleId);
        Result.packageResultMethod(result, articlePo);
        return result;
    }

    /**
     * 获取文章总数（无需登录）
     *
     * @return Result<Integer>
     */
    @RequestMapping(value = "/getCount", method = RequestMethod.GET)
    public Result<Integer> getCount() {
        Result<Integer> result = new Result<>();
        int count = articleService.getCount();
        Result.packageResultMethod(result, count);
        return result;
    }
}


