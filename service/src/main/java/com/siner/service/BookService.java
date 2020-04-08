package com.siner.service;

import com.siner.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAllBook();

    public List<Book> searchBookByName(String bookname);

    public boolean addBook(Book book);

}
