package com.siner.service.impl;


import com.siner.dao.BookDao;
import com.siner.entity.Book;
import com.siner.entity.Manager;
import com.siner.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BookService")
public class BookServiceimpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> findAllBook() {
        System.out.println("开始业务逻辑层的调用（查找所有书）....");

        List<Book> result=bookDao.findAllBook();
        if(result!=null){
            System.out.println("有书本数据 查找成功");
            return result;
        }else {
            return null;
        }
    }

    @Override
    public List<Book> searchBookByName(String bookname) {
        System.out.println("开始业务逻辑层的调用（关键字找书）....");
        List<Book> result=bookDao.searchBookByName(bookname);
        if(result!=null){
            System.out.println("有书本数据 查找成功");
            return result;
        }else {
            return null;
        }
    }

    @Override
    public boolean addBook(Book book) {
        System.out.println("开始业务逻辑层的调用（新增书籍）....");
        int n =bookDao.addBook(book);
        if(n>0){
            System.out.println("新增成功");
            return true;
        }else {
            return false;
        }
    }

}
