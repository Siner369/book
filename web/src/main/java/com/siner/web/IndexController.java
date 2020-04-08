package com.siner.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    /**
     * 打开首页
     * @return
     */
    @RequestMapping("/bookstore/index")
    public String toIndex() {
        return "bookstore/index.html";
    }

    /**
     * 打开后台首页
     * @return
     */
    @RequestMapping("admin/index")
    public String toAdminIndex() {
        return "admin/index";
    }

    @RequestMapping("login")
    public String loginpage() {
        return "admin/login";
    }

}
