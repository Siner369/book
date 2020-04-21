package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private int bid;
    private String bname;
    private double bprice;
    private String bpic;
    private int count;
    private double sumPrice;
}
