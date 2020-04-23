package com.siner.service.impl;


import com.siner.dao.OrdersIteamDao;
import com.siner.entity.Orders;
import com.siner.entity.OrdersIteam;
import com.siner.entity.User;
import com.siner.service.OrdersIteamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrdersIteamService")
public class OrdersIteamServiceImpl implements OrdersIteamService {

    @Autowired
    private OrdersIteamDao ordersIteamDao;

    @Override
    public boolean addOrderIteam(OrdersIteam ordersIteam) {
        System.out.println("开始业务逻辑层的调用（插入订单大表）....");
        int n=ordersIteamDao.addOrderIteam(ordersIteam);
        if(n>0){
            System.out.println("插入大订单成功");
            return true;
        }else {
            return false;
        }
    }


}
