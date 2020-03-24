package com.echo.blog.dao;


import com.echo.blog.po.ProjectCollectionPo;

import java.util.List;

/**
 * @author echo
 * @date 2020-02-14 21:54:49
 */
public interface ProjectCollectionDao {

    int deleteByPrimaryKey(Long id);

    int insert(ProjectCollectionPo record);

    int insertSelective(ProjectCollectionPo record);

    ProjectCollectionPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectCollectionPo record);

    int updateByPrimaryKey(ProjectCollectionPo record);

    List<ProjectCollectionPo> getList();

}