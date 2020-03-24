package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.param.ArticleParam;
import com.echo.blog.po.ArticlePo;
import com.echo.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author echo
 * @date 2020-01-19
 */
@RestController
@RequestMapping("/articleController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 项目健康监测方法
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<String> save(@RequestBody ArticleParam articleParam) {
        Result<String> result = new Result<>();
        String token = articleParam.getToken();
//        try {
//            JwtTokenUtil.verifyToken(token);
//        } catch (Exception e) {
//            result.setStatus(StatusCode.FAIL.getCode());
//            result.setMessage("token过期请重新登录");
//            return result;
//        }
        String response = articleService.save(articleParam);
        Result.packageResultMethod(result, response);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<String> delete(@RequestParam(value = "id") Long id) {
        Result<String> result = new Result<>();
        String response = articleService.delete(id);
        Result.packageResultMethod(result, response);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<String> update(@RequestParam(value = "articlePo") ArticlePo articlePo) {
        Result<String> result = new Result<>();
        String response = articleService.update(articlePo);
        Result.packageResultMethod(result, response);
        return result;
    }

    /**
     * 分页获取文章摘要内容
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
     * 查询5条热门文章
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
     * 根据文章id获取文章详情
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
}
