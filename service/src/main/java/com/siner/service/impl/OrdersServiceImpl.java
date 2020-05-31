package com.siner.service.impl;


import com.siner.dao.OrdersDao;
import com.siner.entity.Book;
import com.siner.entity.Orders;
import com.siner.entity.User;
import com.siner.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public boolean addOrder(Orders order) {
        System.out.println("开始业务逻辑层的调用（插入订单大表）....");
        int n=ordersDao.addOrder(order);
        if(n>0){
            System.out.println("插入大订单成功");
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Orders> findAllOrder(User user) {
        System.out.println("开始业务逻辑层的调用（搜索订单）....");
        /*Map map = new HashMap();
        map.put("uid",user.getUid());*/
        List<Orders> allOrder = ordersDao.findAllOrder(user.getUid());
        return allOrder;
    }

    @Override
    public Orders findOrderByOid(String oid) {
        return ordersDao.findOrderByOid(oid);
    }

    @Override
    public List<Orders> adminFindAllOrder() {
        return ordersDao.adminFindAllOrder();
    }

    @Override
    public List<Orders> findOrderByTel(String tel) {
        return ordersDao.findOrderByTel(tel);
    }

    @Override
    public boolean delOrder(String oid) {
        return ordersDao.delOrder(oid);
    }

}
