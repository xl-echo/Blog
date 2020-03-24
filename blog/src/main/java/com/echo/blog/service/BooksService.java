package com.echo.blog.service;

import com.echo.blog.po.BooksPo;

import java.util.List;

/**
 * @author echo
 * @date 2020-02-07
 */
public interface BooksService {

    /**
     * 获取书籍列表
     *
     * @return List<BooksPo>
     */
    List<BooksPo> getList();

}
