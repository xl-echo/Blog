package com.echo.blog.service;

import com.echo.blog.po.ProjectCollectionPo;

import java.util.List;

/**
 * @author echo
 * @date 2020/2/14 21:59
 **/
public interface ProjectCollectionService {

    /**
     * 获取所有项目列表
     *
     * @return List<ProjectCollectionPo>
     */
    List<ProjectCollectionPo> getList();

    /**
     * 根据id查询项目详情
     *
     * @param id 项目id
     * @return ProjectCollectionPo
     */
    ProjectCollectionPo getByIdProject(Long id);

}
