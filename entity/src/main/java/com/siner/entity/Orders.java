package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders{
    private String oid;//订单ID
    private int uid;//用户ID
    private String uaddress;//地址
    private String utel;//电话
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buytime;//购买时间
    private double sum; //大订单总价
    private int state;//订单状态  0 未付款  1 已经付款 2 订单失效
    private OrdersIteam ordersIteam;   //association
    private Book book;   //association
    private User user;
}
