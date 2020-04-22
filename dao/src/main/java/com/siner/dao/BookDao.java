package com.siner.dao;


import com.siner.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("BookDao")
public interface BookDao {

    //后台 检索全部书籍
    List<Book> findAllBook();

    //后台 关键词检索书籍
    List<Book> searchBookByName(@Param("bookname")String bname);

    //后台 新增书籍
    int addBook(Book book);

    //后台 删除书籍
    int delBook(String bid);

    Book searchBookByID(int bid);

    int updateBook(Book book);

    List<Book> searchByType(String booktype);
}
