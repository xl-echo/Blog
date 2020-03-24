package com.echo.blog.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * project_collection
 * @author echo
 * @date 2020-02-14 21:53:40
 */
@Data
public class ProjectCollectionPo implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目git地址
     */
    private String ssh;

    /**
     * 项目http地址
     */
    private String https;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}