package com.siner.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siner.entity.*;
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
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller("OrdersIteamController")
public class OrdersIteamController {

    @Autowired
    private OrdersIteamService ordersIteamService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private BookService bookService;

    Jedis jedis = JedisPoolUtils.getJedis();

    @PostMapping("/bookstore/addCart")
    public @ResponseBody String addCart(String uid, String bid, String oocount,HttpSession session) {
        //1.从redis中获得值，数据的形式为json字符串
        User user = (User) session.getAttribute("currUser");
        Book book = bookService.searchBookByID(Integer.valueOf(bid));
        String bookCount = jedis.hget("user:" + uid, bid);
        Map<String, String> map = jedis.hgetAll("user:" + user.getUid());
        List<Cart> list = new ArrayList<>();
        map.put(bid, oocount);
        if (null == bookCount) {
            jedis.hmset("user:" + uid, map);
        } else {
            if (Integer.valueOf(oocount) > book.getBnum()) {
                jedis.hmset("user:" + uid, map);
            }
        }
        //查一下购物车
        double cartSum = 0;
        int cartSize = 0;
        for(Map.Entry<String, String> a:map.entrySet()){
            Book b = bookService.searchBookByID(Integer.valueOf(a.getKey()));
            cartSum += book.getBprice() * Double.valueOf(a.getValue());
            cartSize++;
        }
        session.setAttribute("cartSum",cartSum);
        session.setAttribute("cartSize",cartSize);
        //查完
        JSONObject json = new JSONObject();
        json.put("msg","success");
        return json.toString();
    }

    @PostMapping("bookstore/addOrder")
    public @ResponseBody String addOrder(String uid, String bid, String oocount,HttpSession session) throws JsonProcessingException {
        Book book = bookService.searchBookByID(Integer.valueOf(bid));
        String bookCount = jedis.hget("user:" + uid, bid);
        Map<String, String> map = new HashMap<>();
        map.put(bid, oocount);
        if (null == bookCount) {
            jedis.hmset("user:" + uid, map);
        } else {
            if (Integer.parseInt(oocount.trim()) > book.getBnum()) {
                jedis.hmset("user:" + uid, map);
            }
        }
        ////
        //查一下购物车
        double cartSum = 0;
        int cartSize = 0;
        for(Map.Entry<String, String> a:map.entrySet()){
            Book b = bookService.searchBookByID(Integer.valueOf(a.getKey()));
            cartSum += book.getBprice() * Double.valueOf(a.getValue());
            cartSize++;
        }
        session.setAttribute("cartSum",cartSum);
        System.out.println("当前购物车价格："+cartSum);
        session.setAttribute("cartSize",cartSize);
        System.out.println("当前购物车数量："+cartSize);
        //查完
        //
        map.clear();
        Map<String, String> userCart = jedis.hgetAll("user:" + uid);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(userCart);
        return s;
    }

    @RequestMapping("/bookstore/order")
    public String toOrderPage() {
        return "/bookstore/order";
    }

    @RequestMapping("/bookstore/loadOrderPage")
    public @ResponseBody List loadOrderPage(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Map<String, String> map = jedis.hgetAll("user:" + user.getUid());
        List<Cart> list = new ArrayList<>();
        if (map!=null){
            double cartSum = 0;
            for(Map.Entry<String, String> a:map.entrySet()){
                Book book = bookService.searchBookByID(Integer.valueOf(a.getKey()));
                Cart cart = new Cart();
                cart.setBid(Integer.valueOf(a.getKey()));
                cart.setBname(book.getBname());
                cart.setBpic(book.getBpic());
                cart.setBprice(book.getBprice());
                cart.setCount(Integer.valueOf(a.getValue()));
                cart.setSumPrice(Integer.valueOf(a.getValue()) * book.getBprice());
                list.add(cart);
                cartSum += book.getBprice();
            }
            session.setAttribute("cartSum",cartSum);
            session.setAttribute("cartSize",list.size());
        }
        return list;
    }

    @GetMapping("/bookstore/clearCart")
    public String clearCart(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        jedis.del("user:"+user.getUid());
        return "/bookstore/order";
    }

    @GetMapping("/bookstore/orderDel")
    public String orderDel(String bid,HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        jedis.hdel("user:"+user.getUid(),bid);
        return "/bookstore/order";
    }

    @RequestMapping("/admin/order_detail")
    public String to_order_detail(Model model,String oid){
        Orders orders = ordersService.findOrderByOid(oid);
        System.out.println(orders);
        model.addAttribute("orderInfo",orders);
        List<OrdersIteam> list = ordersIteamService.FindOrdersIteam(oid);
        System.out.println("找到了详细订单列表："+list);
        model.addAttribute("IteamList",list);
        return "/admin/order_detail";
    }

}
