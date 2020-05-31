package com.siner.dao;


import com.siner.entity.Orders;
import com.siner.entity.OrdersIteam;
import com.siner.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrdersIteam")
public interface OrdersIteamDao {
    int addOrderIteam(OrdersIteam ordersIteam);


    List<OrdersIteam> FindOrdersIteam(String oid);
}
