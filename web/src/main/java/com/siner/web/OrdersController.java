package com.siner.web;


import com.siner.entity.User;
import com.siner.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller("OrdersController")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


}
