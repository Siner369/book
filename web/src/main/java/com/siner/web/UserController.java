package com.siner.web;


import com.alibaba.fastjson.JSONObject;
import com.siner.entity.User;
import com.siner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/doReg")
    public @ResponseBody
    boolean doReg(@RequestBody User user) {
        boolean ok = userService.reg(user);
        System.out.println("新增成功了吗？" + ok);
        if (ok) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "doLogin", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String doLogin(String uname, String upass) {
        User webUser = new User();
        webUser.setUname(uname);
        webUser.setUpass(upass);
        User u = userService.login(webUser);

        JSONObject json = (JSONObject) JSONObject.toJSON(u);
        if (json != null) {
            return json.toString();
        } else {
            JSONObject j = new JSONObject();
            j.put("msg", "ISNULL");
            return j.toString();
        }
    }

    @RequestMapping(value = "checkDouble", method = {RequestMethod.POST})
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
    }


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