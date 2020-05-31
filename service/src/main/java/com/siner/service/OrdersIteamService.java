package com.siner.service;

import com.siner.entity.Orders;
import com.siner.entity.OrdersIteam;
import com.siner.entity.User;

import java.util.List;

public interface OrdersIteamService {

    boolean addOrderIteam(OrdersIteam ordersIteam);


    List<OrdersIteam> FindOrdersIteam(String oid);
}
