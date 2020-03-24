package com.echo.blog.dao;


import com.echo.blog.po.BooksPo;

import java.util.List;

public interface BooksDao {

    int deleteByPrimaryKey(Long id);

    int insert(BooksPo record);

    int insertSelective(BooksPo record);

    BooksPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BooksPo record);

    int updateByPrimaryKey(BooksPo record);

    /**
     * 获取书籍列表
     *
     * @return List<BooksPo>
     */
    List<BooksPo> getList();

}