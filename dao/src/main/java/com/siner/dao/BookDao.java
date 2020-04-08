package com.siner.dao;


import com.siner.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("BookDao")
public interface BookDao {

   /* //前台  关键词检索
    public List<Book> keyWordSearch(String bname);

    //前台  分类检索
    public List<Book> categorySearch(String booktype);
*/
    //后台 检索全部书籍
    public List<Book> findAllBook();

    //后台 关键词检索书籍
    public List<Book> searchBookByName(@Param("bookname")String bname);

    //后台 新增书籍
    public int addBook(Book book);

}
