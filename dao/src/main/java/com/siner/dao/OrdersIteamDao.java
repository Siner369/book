package com.siner.dao;


import com.siner.entity.OrdersIteam;
import org.springframework.stereotype.Repository;

@Repository("OrdersIteam")
public interface OrdersIteamDao {
    int addOrderIteam(OrdersIteam ordersIteam);
}
