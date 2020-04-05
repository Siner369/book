package com.siner.service.impl;


import com.siner.dao.BookDao;
import com.siner.entity.Book;
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


    /*@Override
    public int getCounts(Book book) {
        return bookDao.getCounts(book);
    }

    @Override
    public List<Book> getListByPage(Pager pager, Book book) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("book",book);
        map.put("pageStart",1);
        map.put("pageEnd",10000);
        int n = bookDao.getListByPage(map).size();
        pager.setTotalCounts(n);
        map.put("pageStart",(pager.getCurrPage()-1)*pager.getPageSize()+1);
        map.put("pageEnd",pager.getCurrPage()*pager.getPageSize());
        List<Book> list= bookDao.getListByPage(map);
        return list;
    }*/
}
