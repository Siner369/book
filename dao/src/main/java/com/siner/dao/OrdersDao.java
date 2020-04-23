package com.siner.dao;


import com.siner.entity.Orders;
import com.siner.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("OrdersDao")
public interface OrdersDao {

    int addOrder(Orders order);

    List<Orders> findAllOrder(Map map);
}
