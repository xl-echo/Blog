package com.echo.blog.service.impl;

import com.echo.blog.dao.LikeDao;
import com.echo.blog.po.LikePo;
import com.echo.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 点赞服务实现
 * @author echo
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDao likeDao;

    @Override
    public String likeArticle(Long articleId, Long userId, String username) {
        // 检查是否已点赞
        LikePo existingLike = likeDao.selectByArticleIdAndUserId(articleId, userId);
        if (existingLike != null) {
            return "已经点赞过了";
        }

        // 创建点赞记录
        LikePo like = new LikePo();
        like.setArticleId(articleId);
        like.setUserId(userId);
        like.setUsername(username);
        like.setCreateTime(new Date());
        likeDao.insert(like);

        return "点赞成功";
    }

    @Override
    public String unlikeArticle(Long articleId, Long userId) {
        int result = likeDao.deleteByArticleIdAndUserId(articleId, userId);
        if (result > 0) {
            return "取消点赞成功";
        }
        return "取消点赞失败";
    }

    @Override
    public boolean isLiked(Long articleId, Long userId) {
        LikePo like = likeDao.selectByArticleIdAndUserId(articleId, userId);
        return like != null;
    }

    @Override
    public int getLikeCount(Long articleId) {
        return likeDao.countByArticleId(articleId);
    }
}





