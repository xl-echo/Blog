package com.echo.blog.service;

import com.echo.blog.param.ArticleParam;
import com.echo.blog.po.ArticlePo;

import java.util.List;

/**
 * @author echo
 * @date 2020-01-19 21:31:56
 */
public interface ArticleService {

    /**
     * 项目健康监测方法
     *
     * @return String
     */
    String test();

    /**
     * 添加一篇文章
     *
     * @param articleParam 文章内容
     * @return String
     */
    String save(ArticleParam articleParam);

    /**
     * 根据id删除一篇文章
     *
     * @param id 文章id
     * @return String
     */
    String delete(Long id);

    /**
     * 根据id修改一篇文章
     *
     * @param articlePo 文章id
     * @return String
     */
    String update(ArticlePo articlePo);

    /**
     * 分页查询文章
     *
     * @param articleParam 查询条件
     * @return List<ArticlePo>
     */
    List<ArticlePo> getList(ArticleParam articleParam);

    /**
     * 根据文章id获取文章详情
     *
     * @param articleId 文章id
     * @return ArticlePo
     */
    ArticlePo getDetail(Integer articleId);

    /**
     * 查询5条热门文章
     *
     * @return List<ArticlePo>
     */
    List<ArticlePo> getHotList();

}
