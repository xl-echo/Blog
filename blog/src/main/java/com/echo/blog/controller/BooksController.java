package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.po.BooksPo;
import com.echo.blog.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author echo
 * @date 2020-02-07
 */
@RestController
@RequestMapping("/booksController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BooksController {

    private final BooksService booksService;

    /**
     * 获取书籍列表
     *
     * @return Result<List<BooksPo>>
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<List<BooksPo>> getList() {
        Result<List<BooksPo>> result = new Result<>();
        List<BooksPo> response = booksService.getList();
        Result.packageResultMethod(result, response);
        return result;
    }

}
