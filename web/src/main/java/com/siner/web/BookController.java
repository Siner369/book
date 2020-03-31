package com.siner.web;

import com.siner.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("BookController")
public class BookController {
    @Autowired
    private BookService bookService;

}
