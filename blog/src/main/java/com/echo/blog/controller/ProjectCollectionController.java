package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.po.ProjectCollectionPo;
import com.echo.blog.service.ProjectCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author echo
 * @date 2020/2/14 21:57
 **/
@RestController
@RequestMapping("/projectCollectionController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectCollectionController {

    private final ProjectCollectionService projectCollectionService;

    /**
     * 查询所有项目列表
     *
     * @return Result<List < ArticlePo>>
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<List<ProjectCollectionPo>> getList() {
        Result<List<ProjectCollectionPo>> result = new Result<>();
        List<ProjectCollectionPo> projectCollectionPos = projectCollectionService.getList();
        Result.packageResultMethod(result, projectCollectionPos);
        return result;
    }

    /**
     * 根据id查询具体的项目信息
     *
     * @return Result<ProjectCollectionPo>
     */
    @RequestMapping(value = "/getByIdProject", method = RequestMethod.GET)
    public Result<ProjectCollectionPo> getByIdProject(@RequestParam(value = "id") Long id) {
        Result<ProjectCollectionPo> result = new Result<>();
        ProjectCollectionPo projectCollectionPo = projectCollectionService.getByIdProject(id);
        Result.packageResultMethod(result, projectCollectionPo);
        return result;
    }

}
