package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders_iteam {
    private int ooid;//订单物品表ID
    private int bid;//书籍ID
    private int oocount;//购买数量
    private double totle;//总价

}
