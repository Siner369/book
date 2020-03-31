package com.siner.web;


import com.siner.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("CommentsController")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
}
