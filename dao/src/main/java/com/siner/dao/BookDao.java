package com.siner.dao;


import com.siner.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("BookDao")
public interface BookDao {

   /* //前台  关键词检索
    public List<Book> keyWordSearch(String bname);

    //前台  分类检索
    public List<Book> categorySearch(String booktype);

    //后台 关键词检索
    public List<Book> admin_keyWordSearch(String bname);
*/
}
