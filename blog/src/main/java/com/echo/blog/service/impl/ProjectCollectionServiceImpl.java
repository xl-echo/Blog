package com.echo.blog.service.impl;

import com.echo.blog.dao.ProjectCollectionDao;
import com.echo.blog.po.ProjectCollectionPo;
import com.echo.blog.service.ProjectCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author echo
 * @date 2020/2/14 22:00
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectCollectionServiceImpl implements ProjectCollectionService {

    private final ProjectCollectionDao projectCollectionDao;

    @Override
    public List<ProjectCollectionPo> getList() {
        return projectCollectionDao.getList();
    }

    @Override
    public ProjectCollectionPo getByIdProject(Long id) {
        return projectCollectionDao.selectByPrimaryKey(id);
    }

}
