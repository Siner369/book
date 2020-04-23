package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersIteam {
    private int ooid;//订单物品表ID
    private String oid;//订单大表ID
    private int bid;//书籍ID
    private int oocount;//购买数量
    private double IteamSum; //小订单总价

}
