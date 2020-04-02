package com.siner.web;

import com.alibaba.fastjson.JSONObject;
import com.siner.entity.Manager;
import com.siner.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *@RestController是@ResponseBody和@Controller的组合注解
 @Controller是用来响应页面的，如果是string类型的方法，则springmvc会跳转到相应的页面（视图）
 @ResponseBody是用来响应数据的，如果是对象类型的方法，则springmvc会将结果对象转成json格式输出给前端
 本例中我使用的是@RestController注解，所以springmvc会将返回的对象Result自动转json返回给前端（底层默认是使用jsckson来实现数据格式转换的）
 */
@RestController
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    //执行管理员登录方法
    @RequestMapping(value = "/login", method = {RequestMethod.POST}
            , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String Managerlogin(@RequestBody Manager manager, HttpServletRequest request) {
        System.out.println("管理登录");
        Manager m = managerService.login_admin(manager);
        request.getSession().setAttribute("manager","true");
        System.out.println("管理员登录方法执行，传递的参数为" + JSONObject.toJSONString(m));
        return JSONObject.toJSONString(m);
    }
    
    //退出并清除cookie
    @RequestMapping("admin_logout")
    public String logOut(HttpServletRequest request){
        System.out.println("管理员账号退出方法执行了...");
        JSONObject jo = new JSONObject();
        //判断seesion是否存在，不存在则退出
        if (request.getSession().getAttribute("manager")!=null){
            request.getSession().removeAttribute("manager");
            return jo.put("logout","success").toString();
        }else{
            return jo.put("logout","error").toString();
        }
    }
}
