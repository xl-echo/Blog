package com.echo.blog.service.impl;

import com.echo.blog.dao.ArticleDao;
import com.echo.blog.param.ArticleParam;
import com.echo.blog.po.ArticlePo;
import com.echo.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author echo
 * @date 2020-01-20 21:57:58
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;

    @Override
    public String test() {
        return "success";
    }

    @Override
    public String save(ArticleParam articleParam) {
        ArticlePo articlePo = new ArticlePo();
        articlePo.setTitle(articleParam.getTitle());
        articlePo.setAuthorId(articleParam.getAuthorId());
        articlePo.setAuthor(articleParam.getAuthor());
        articlePo.setContent(articleParam.getContent());
        articlePo.setClicks(0L);
        articlePo.setIfOriginal(articleParam.getIfOriginal());
        articlePo.setCreateTime(new Date());
        articlePo.setUpdateTime(new Date());
        int insert = articleDao.insert(articlePo);
        return "success";
    }

    @Override
    public String delete(Long id) {
        int i = articleDao.deleteByPrimaryKey(id);
        return "success";
    }

    @Override
    public String update(ArticlePo articlePo) {
        int i = articleDao.updateByPrimaryKey(articlePo);
        return "success";
    }

    @Override
    public List<ArticlePo> getList(ArticleParam articleParam) {
        articleParam.setPage(articleParam.getPage() * articleParam.getSize());
        List<ArticlePo> articlePos = articleDao.getList(articleParam);
        articlePos.forEach(it -> {
            if (it.getContent().length() > 100) {
                it.setContent(it.getContent().substring(0, 100) + "...");
            }
        });
        return articlePos;
    }

    @Override
    public ArticlePo getDetail(Integer articleId) {
        ArticlePo articlePo = articleDao.getDetail(articleId);
        articlePo.setClicks(articlePo.getClicks() + 1);
        articleDao.updateByPrimaryKeySelective(articlePo);
        return articlePo;
    }

    @Override
    public List<ArticlePo> getHotList() {
        return articleDao.getHotList();
    }

}
