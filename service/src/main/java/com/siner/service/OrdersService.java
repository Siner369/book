package com.siner.service;

import com.siner.entity.Book;
import com.siner.entity.Orders;
import com.siner.entity.User;

import java.util.List;

public interface OrdersService {
    boolean addOrder(Orders order);

    List<Orders> findAllOrder(User user);

    Orders findOrderByOid(String oid);

    List<Orders> adminFindAllOrder();

    List<Orders> findOrderByTel(String tel);

    boolean delOrder(String oid);
}
