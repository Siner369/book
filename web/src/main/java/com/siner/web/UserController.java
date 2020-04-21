package com.siner.web;


import com.alibaba.fastjson.JSONObject;
import com.siner.entity.User;
import com.siner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/doReg")
    public @ResponseBody
    boolean doReg(@RequestBody User user,HttpSession session) {
        boolean ok = userService.reg(user);
        System.out.println("新增成功了吗？" + ok);
        if (ok) {
            session.setAttribute("currUser",user);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/bookstore/login")
    public String toLogin() {
        return "/bookstore/login";
    }

    @RequestMapping("/bookstore/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("currUser");
        return "bookstore/login";
    }

    @RequestMapping("/bookstore/reg")
    public String toReg() {
        return "bookstore/reg";
    }

    @RequestMapping("/bookstore/vip")
    public String toVip() {
        return "bookstore/vip";
    }

    @RequestMapping(value = "doLogin", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String doLogin(String uname, String upass, HttpSession session) {
        User webUser = new User();
        webUser.setUname(uname);
        webUser.setUpass(upass);
        User u = userService.login(webUser);
        session.setAttribute("currUser",u);
        JSONObject json = (JSONObject) JSONObject.toJSON(u);
        if (json != null) {
            return json.toString();
        } else {
            JSONObject j = new JSONObject();
            j.put("msg", "ISNULL");
            return j.toString();
        }
    }
    //个人信息完善
    @PostMapping("/bookstore/compleInfo")
    public  String compleInfo(String uid,String uaddress, String umailcode,String ugender, HttpSession session) {
        User u = new User();
        u.setUid(Integer.valueOf(uid));
        u.setUaddress(uaddress);
        u.setUmailcode(Integer.valueOf(umailcode));
        u.setUgender(ugender);
        System.out.println(u);
        userService.updateUserInfo(u);
        session.setAttribute("currUser",u);
        JSONObject json = new JSONObject();
        json.put("msg","success");
        return json.toString();

    }

    /*@RequestMapping(value = "checkDouble", method = {RequestMethod.POST})
    public @ResponseBody
    String checkDouble(@RequestBody String uname) {
        User u = new User();
        u.setUname(uname);
        boolean canUse = userService.checkDouble(u);
        JSONObject object = new JSONObject();
        if (canUse) {
            object.put("msg", "no");
            System.out.println(object);
        } else {
            object.put("msg", "yes");
        }

        return object.toString();
    }*/


    //加载信息
    @RequestMapping(value = "loadInfo", method = {RequestMethod.POST})
    public @ResponseBody
    String loadInfo(@RequestBody String uname) {
        User u = userService.loadInfo(uname);
        JSONObject object = new JSONObject();
        return object.toString();
    }

    //更新个人信息
    @RequestMapping("/doUpdate")
    public @ResponseBody
    String updateUserInfo(@RequestBody User user) {
        int n = userService.updateUserInfo(user);
        JSONObject object = new JSONObject();
        if (n>0) {
            object.put("update", "success");
            System.out.println(object);
        } else {
            object.put("update", "error");
        }

        return object.toString();
    }
}