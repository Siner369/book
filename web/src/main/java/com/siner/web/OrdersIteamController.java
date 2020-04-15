package com.siner.web;



import com.siner.service.OrdersIteamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller("Orders_iteamController")
public class OrdersIteamController {

    @Autowired
    private OrdersIteamService orders_iteamService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void test(){
        redisTemplate.boundValueOps("");
    }

}
