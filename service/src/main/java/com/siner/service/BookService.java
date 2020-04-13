package com.siner.service;

import com.siner.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBook();

    List<Book> searchBookByName(String bookname);

    boolean addBook(Book book);

    boolean delBook(String bid);

    Book searchBookByID(int bid);

    boolean updateBook(Book book);
}
