package com.siner.web;


import com.alibaba.fastjson.JSONObject;
import com.siner.entity.User;
import com.siner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/doReg")
    public @ResponseBody boolean doReg(@RequestBody User user,HttpSession session) {
        boolean ok = userService.reg(user);
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

    @PostMapping(value = "doLogin",produces = "application/json;charset=UTF-8")
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
    public @ResponseBody String compleInfo(String uid,String uaddress,String utel, String umailcode,String ugender, HttpSession session) {
        User u = new User();
        u.setUid(Integer.valueOf(uid));
        u.setUtel(utel);
        u.setUaddress(uaddress);
        u.setUmailcode(Integer.valueOf(umailcode));
        u.setUgender(ugender);
        int i = userService.updateUserInfo(u);
        session.setAttribute("currUser",u);
        JSONObject json = new JSONObject();
        if (i!=0) json.put("msg","success");
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

    @GetMapping("/admin/user_list")
    public String user_list() {
        return "/admin/user_list";
    }

    @GetMapping("/admin/allUser")
    public @ResponseBody String allUser() {
        List<User> list = userService.allUser();
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);
        return json.toString();
    }

    @PostMapping("/delUser")
    public @ResponseBody String delUser(String uid) {
        int del = userService.delUser(Integer.valueOf(uid));
        JSONObject json = new JSONObject();
        json.put("del",del);
        return json.toString();
    }

    @GetMapping("/LockUser")
    public @ResponseBody String LockUser(String uid) {
        int lock = userService.LockUser(Integer.valueOf(uid));
        JSONObject json = new JSONObject();
        json.put("lock",lock);
        return json.toString();
    }

    @GetMapping("/UnLockUser")
    public @ResponseBody String UnLockUser(String uid) {
        int unlock = userService.UnLockUser(Integer.valueOf(uid));
        JSONObject json = new JSONObject();
        json.put("unlock",unlock);
        return json.toString();
    }

}