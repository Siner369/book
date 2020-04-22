package com.siner.dao;


import com.siner.entity.Orders;
import org.springframework.stereotype.Repository;

@Repository("OrdersDao")
public interface OrdersDao {
    int addOrder(Orders order);
}
