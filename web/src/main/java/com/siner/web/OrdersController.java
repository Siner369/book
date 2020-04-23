package com.siner.web;


import com.siner.entity.Book;
import com.siner.entity.Orders;
import com.siner.entity.OrdersIteam;
import com.siner.entity.User;
import com.siner.service.BookService;
import com.siner.service.OrdersIteamService;
import com.siner.service.OrdersService;
import com.siner.util.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller("OrdersController")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersIteamService ordersIteamService;

    @Autowired
    private BookService bookService;

    Jedis jedis = JedisPoolUtils.getJedis();

    @RequestMapping("/bookstore/order2")
    public String toOrder2() {
        return "/bookstore/order2";
    }

    @RequestMapping("/bookstore/orderSubmit")
    public String orderSubmit(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Map<String, String> map = jedis.hgetAll("user:" + user.getUid());
        Orders order = new Orders();
        order.setUid(user.getUid());
        order.setUtel(user.getUtel());
        order.setUaddress(user.getUaddress());
        order.setBuytime(new Date());
        order.setState(0);
        String uuid =  UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
        order.setOid(uuid);

        double sum = 0;
        for(Map.Entry<String, String> a:map.entrySet()){
            double IteamSum = 0;
            Book book = bookService.searchBookByID(Integer.valueOf(a.getKey()));
            IteamSum += Integer.valueOf(a.getValue()) * book.getBprice();
            sum += IteamSum;
        }
        order.setSum(sum);
        boolean b1 = ordersService.addOrder(order);
        if (b1){
            System.out.println("订单大表插入成功");
        } else {
            System.out.println("订单大表插入失败");
        }

        for(Map.Entry<String, String> a:map.entrySet()){
            double IteamSum = 0;
            OrdersIteam ordersIteam = new OrdersIteam();
            ordersIteam.setBid(Integer.valueOf(a.getKey()));
            ordersIteam.setOid(uuid);
            ordersIteam.setOocount(Integer.valueOf(a.getValue()));
            System.out.println("正在插入订单详情表："+ordersIteam);
            Book book = bookService.searchBookByID(Integer.valueOf(a.getKey()));
            IteamSum += Integer.valueOf(a.getValue()) * book.getBprice();
            ordersIteam.setIteamSum(IteamSum);
            boolean b2 = ordersIteamService.addOrderIteam(ordersIteam);
            if (b2) {
                System.out.println("订单详情插入成功");
                jedis.hdel("user:"+user.getUid(),a.getKey());
            }else{
                System.out.println("订单详情插入失败");
            }
        }

        return "/bookstore/success";
    }

    @RequestMapping("/bookstore/vipOrder")
    public String vipOrder(HttpSession session,Model model) {
        User user = (User) session.getAttribute("currUser");
        List<Orders> allOrder = ordersService.findAllOrder(user);
        System.out.println(allOrder);
        model.addAttribute("orderList",allOrder);
        return "/bookstore/vipOrder";
    }



}
