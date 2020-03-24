package com.echo.blog.dao;

import com.echo.blog.param.ArticleParam;
import com.echo.blog.po.ArticlePo;

import java.util.List;

/**
 * @author echo
 * @date 2020-01-19
 */
public interface ArticleDao {

    /**
     * 获取所有的文章
     *
     * @return List<ArticlePo>
     */
    List<ArticlePo> getAll();

    int deleteByPrimaryKey(Long id);

    /**
     * 添加文章
     *
     * @param articlePo
     * @return int
     */
    int insert(ArticlePo articlePo);

    int insertSelective(ArticlePo record);

    ArticlePo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticlePo record);

    int updateByPrimaryKeyWithBLOBs(ArticlePo record);

    int updateByPrimaryKey(ArticlePo record);

    /**
     * 分页查询
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