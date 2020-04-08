package com.siner.web;

import com.alibaba.fastjson.JSONObject;
import com.siner.entity.Manager;
import com.siner.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *@RestController是@ResponseBody和@Controller的组合注解
 @Controller是用来响应页面的，如果是string类型的方法，则springmvc会跳转到相应的页面（视图）
 @ResponseBody是用来响应数据的，如果是对象类型的方法，则springmvc会将结果对象转成json格式输出给前端
 本例中我使用的是@RestController注解，所以springmvc会将返回的对象Result自动转json返回给前端（底层默认是使用jsckson来实现数据格式转换的）
 */
@Controller
@RequestMapping("admin")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    //执行管理员登录方法
    @PostMapping(value = "/login")
    public String Managerlogin(String mname, String mpass,HttpSession session) {
        System.out.println("后台登录");
        Manager m = managerService.login_admin(mname,mpass);
        System.out.println(m);
        String type = null;
        if (m.getUsertype()==0) type = "(管理员)";
        else type = "(商家)";
        if (null != m) {
            session.setAttribute("admin", "true");
            session.setAttribute("mname",m.getMname()+type);
            session.setAttribute("usertype",m.getUsertype());
            return "redirect:/admin/index";
        } else {
            return  "admin/login";
        }
    }
    //退出并清除cookie
    @RequestMapping("/logout")
    public @ResponseBody String logOut(HttpServletRequest request){
        System.out.println("后台注销");
        JSONObject jo = new JSONObject();
        //判断seesion是否存在，不存在则退出
        if (request.getSession().getAttribute("admin")!=null){
            request.getSession().removeAttribute("admin");
            jo.put("logout","success");
        }else{
            jo.put("logout","error");
        }
        return jo.toString();
    }
}
