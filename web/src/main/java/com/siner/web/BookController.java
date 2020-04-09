package com.siner.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.siner.entity.Book;
import com.siner.entity.Manager;
import com.siner.entity.User;
import com.siner.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller("BookController")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("findAllBook")
    public String findAll(Model model, HttpSession session) {

        List<Book> list = new ArrayList<Book>();
        list = bookService.findAllBook();
        System.out.println(list);
        model.addAttribute("books",list);
        return "admin/product_list";
    }
    //执行管理员登录方法
    @RequestMapping(value = "searchByName", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List searchByName(String bookname,HttpSession session) {
        System.out.println(bookname);
        List<Book> list = bookService.searchBookByName(bookname);
        System.out.println("controller 关键字结果输出："+list);

        return list;
    }

    @RequestMapping("addPage")
    public String addPage() {

        return "admin/product_add";
    }


    @RequestMapping(value = "addBook", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String addBook(@RequestBody Book book) {
        System.out.println(book);
        boolean addFlag = bookService.addBook(book);
        JSONObject json = new JSONObject();
        if (addFlag) {
            json.put("add","success");
            return json.toString();
        } else {
            json.put("add","error");
            return json.toString();
        }
    }




}
