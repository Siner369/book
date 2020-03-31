package com.siner.web;



import com.siner.service.Orders_iteamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("Orders_iteamController")
public class Orders_iteamController {

    @Autowired
    private Orders_iteamService orders_iteamService;

}
