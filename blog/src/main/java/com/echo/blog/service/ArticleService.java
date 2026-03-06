package com.echo.blog.service;

import com.echo.blog.param.ArticleParam;
import com.echo.blog.po.ArticlePo;

import java.util.List;

/**
 * 文章服务接口
 * @author echo
 */
public interface ArticleService {

    /**
     * 根据ID获取文章
     * @param id 文章ID
     * @return 文章信息
     */
    ArticlePo getArticleById(Long id);

    /**
     * 获取所有文章
     * @return 文章列表
     */
    List<ArticlePo> getAllArticles();

    /**
     * 获取热门文章
     * @return 热门文章列表
     */
    List<ArticlePo> getHotArticles();

    /**
     * 创建文章
     * @param article 文章信息
     * @return 创建的文章
     */
    ArticlePo createArticle(ArticlePo article);

    /**
     * 更新文章
     * @param article 文章信息
     * @return 更新后的文章
     */
    ArticlePo updateArticle(ArticlePo article);

    /**
     * 删除文章
     * @param id 文章ID
     */
    void deleteArticle(Long id);

    /**
     * 按分类获取文章
     * @param categoryId 分类ID
     * @return 文章列表
     */
    List<ArticlePo> getArticlesByCategory(Long categoryId);

    /**
     * 按标签获取文章
     * @param tagId 标签ID
     * @return 文章列表
     */
    List<ArticlePo> getArticlesByTag(Long tagId);

    /**
     * 测试方法
     * @return 测试结果
     */
    String test();

    /**
     * 保存文章
     * @param articleParam 文章参数
     * @return 保存结果
     */
    String save(ArticleParam articleParam);

    /**
     * 删除文章
     * @param id 文章ID
     * @return 删除结果
     */
    String delete(Long id);

    /**
     * 更新文章
     * @param articlePo 文章对象
     * @return 更新结果
     */
    String update(ArticlePo articlePo);

    /**
     * 分页获取文章列表
     * @param articleParam 查询参数
     * @return 文章列表
     */
    List<ArticlePo> getList(ArticleParam articleParam);

    /**
     * 获取热门文章列表
     * @return 热门文章列表
     */
    List<ArticlePo> getHotList();

    /**
     * 获取文章详情
     * @param articleId 文章ID
     * @return 文章详情
     */
    ArticlePo getDetail(Integer articleId);

    /**
     * 获取文章总数
     * @return 文章总数
     */
    int getCount();
}



