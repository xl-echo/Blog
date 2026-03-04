package com.echo.blog.service.impl;

import com.echo.blog.dao.ArticleDao;
import com.echo.blog.param.ArticleParam;
import com.echo.blog.po.ArticlePo;
import com.echo.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章服务实现类
 * @author echo
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 根据ID获取文章
     * 使用Spring Cache缓存
     * @param id 文章ID
     * @return 文章信息
     */
    @Override
    public ArticlePo getArticleById(Long id) {
        ArticlePo article = articleDao.selectByPrimaryKey(id);
        if (article != null) {
            // 增加阅读量
            article.setViews(article.getViews() + 1);
            articleDao.updateByPrimaryKey(article);
        }
        return article;
    }

    /**
     * 获取所有文章
     * 使用Spring Cache缓存
     * @return 文章列表
     */
    @Override
    public List<ArticlePo> getAllArticles() {
        return articleDao.selectAll();
    }

    /**
     * 获取热门文章
     * 使用Spring Cache缓存
     * @return 热门文章列表
     */
    @Override
    public List<ArticlePo> getHotArticles() {
        return articleDao.selectHotArticles();
    }

    /**
     * 创建文章
     * @param article 文章信息
     * @return 创建的文章
     */
    @Override
    public ArticlePo createArticle(ArticlePo article) {
        articleDao.insert(article);
        return article;
    }

    /**
     * 更新文章
     * @param article 文章信息
     * @return 更新后的文章
     */
    @Override
    public ArticlePo updateArticle(ArticlePo article) {
        articleDao.updateByPrimaryKey(article);
        return article;
    }

    /**
     * 删除文章
     * @param id 文章ID
     */
    @Override
    public void deleteArticle(Long id) {
        articleDao.deleteByPrimaryKey(id);
    }

    /**
     * 按分类获取文章
     * 使用Spring Cache缓存
     * @param categoryId 分类ID
     * @return 文章列表
     */
    @Override
    public List<ArticlePo> getArticlesByCategory(Long categoryId) {
        return articleDao.selectByCategoryId(categoryId);
    }

    /**
     * 按标签获取文章
     * 使用Spring Cache缓存
     * @param tagId 标签ID
     * @return 文章列表
     */
    @Override
    public List<ArticlePo> getArticlesByTag(Long tagId) {
        return articleDao.selectByTagId(tagId);
    }

    /**
     * 清空所有文章缓存
     */
    public void clearAll$1Cache() { }

    /**
     * 测试方法
     */
    @Override
    public String test() {
        return "文章服务测试成功";
    }

    /**
     * 保存文章
     */
    @Override
    public String save(ArticleParam articleParam) {
        ArticlePo article = new ArticlePo();
        // 将ArticleParam转换为ArticlePo
        article.setTitle(articleParam.getTitle());
        article.setContent(articleParam.getContent());
        article.setAuthorId(articleParam.getAuthorId());
        article.setAuthorName(articleParam.getAuthorName());
        article.setCategoryId(articleParam.getCategoryId());
        article.setTags(articleParam.getTags());
        article.setViews(0L); // 使用Long类型的0L
        article.setLikes(0L); // 使用Long类型的0L

        articleDao.insert(article);
        return "保存成功";
    }

    /**
     * 删除文章
     */
    @Override
    public String delete(Long id) {
        articleDao.deleteByPrimaryKey(id);
        return "删除成功";
    }

    /**
     * 更新文章
     */
    @Override
    public String update(ArticlePo articlePo) {
        articleDao.updateByPrimaryKey(articlePo);
        return "更新成功";
    }

    /**
     * 分页获取文章列表
     */
    @Override
    public List<ArticlePo> getList(ArticleParam articleParam) {
        return articleDao.selectByPage(articleParam);
    }

    /**
     * 获取热门文章列表
     */
    @Override
    public List<ArticlePo> getHotList() {
        return articleDao.selectHotArticles();
    }

    /**
     * 获取文章详情
     */
    @Override
    public ArticlePo getDetail(Integer articleId) {
        ArticlePo article = articleDao.selectByPrimaryKey(articleId.longValue());
        if (article != null) {
            // 增加阅读量
            article.setViews(article.getViews() + 1);
            articleDao.updateByPrimaryKey(article);
        }
        return article;
    }
}





