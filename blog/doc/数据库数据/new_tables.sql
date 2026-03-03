-- 创建评论表
CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `user_id` bigint(20) NOT NULL COMMENT '评论者id',
  `username` varchar(50) DEFAULT NULL COMMENT '评论者用户名',
  `content` text COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- 创建点赞表
CREATE TABLE IF NOT EXISTS `like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `user_id` bigint(20) NOT NULL COMMENT '点赞者id',
  `username` varchar(50) DEFAULT NULL COMMENT '点赞者用户名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_article_user` (`article_id`,`user_id`),
  KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';
