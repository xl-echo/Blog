package com.echo.blog.service.impl;

import com.echo.blog.dao.BooksDao;
import com.echo.blog.po.BooksPo;
import com.echo.blog.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author echo
 * @date 2020/2/7 15:27
 **/
@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksDao booksDao;

    @Override
    public List<BooksPo> getList() {
        return booksDao.getList();
    }

}





