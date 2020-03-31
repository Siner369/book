package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders{
    private int oid;//订单ID
    private int uid;//用户ID
    private int ooid;//订单物品表ID
    private String uaddres;//地址
    private String utel;//电话
    private Date buytime;//购买时间
    private int state;//订单状态  0 未付款  1 已经付款 2 订单失效
}
