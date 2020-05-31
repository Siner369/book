package com.siner.dao;


import com.siner.entity.Book;
import com.siner.entity.Orders;
import com.siner.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("OrdersDao")
public interface OrdersDao {

    int addOrder(Orders order);

    List<Orders> findAllOrder(@Param("uid") int uid);

    Orders findOrderByOid(String oid);

    List<Orders> adminFindAllOrder();

    List<Orders> findOrderByTel(String tel);

    boolean delOrder(String oid);
    // List<Orders> findAllOrder(User user);
}
