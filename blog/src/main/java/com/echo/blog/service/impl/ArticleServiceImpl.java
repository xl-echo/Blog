package com.echo.blog.service.impl;

import com.echo.blog.dao.ArticleDao;
import com.echo.blog.po.ArticlePo;
import com.echo.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章服务实现类
 * 使用Spring Cache替代Redis
 * @author echo
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;

    /**
     * 根据ID获取文章
     * 使用Spring Cache缓存
     * @param id 文章ID
     * @return 文章信息
     */
    @Override
    @Cacheable(value = "articles", key = "#id", unless = "#result == null")
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
    @Cacheable(value = "articles", key = "'all'")
    public List<ArticlePo> getAllArticles() {
        return articleDao.selectAll();
    }

    /**
     * 获取热门文章
     * 使用Spring Cache缓存
     * @return 热门文章列表
     */
    @Override
    @Cacheable(value = "articles", key = "'hot'")
    public List<ArticlePo> getHotArticles() {
        return articleDao.selectHotArticles();
    }

    /**
     * 创建文章
     * @param article 文章信息
     * @return 创建的文章
     */
    @Override
    @CachePut(value = "articles", key = "#result.id")
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
    @CachePut(value = "articles", key = "#article.id")
    public ArticlePo updateArticle(ArticlePo article) {
        articleDao.updateByPrimaryKey(article);
        return article;
    }

    /**
     * 删除文章
     * @param id 文章ID
     */
    @Override
    @CacheEvict(value = "articles", key = "#id")
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
    @Cacheable(value = "articles", key = "'category:' + #categoryId")
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
    @Cacheable(value = "articles", key = "'tag:' + #tagId")
    public List<ArticlePo> getArticlesByTag(Long tagId) {
        return articleDao.selectByTagId(tagId);
    }

    /**
     * 清空所有文章缓存
     */
    @CacheEvict(value = "articles", allEntries = true)
    public void clearAllArticleCache() {
        // 清空缓存
    }
}
