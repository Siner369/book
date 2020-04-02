package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bid;
    private String bname;
    private String booktype;
    private int mid;
    private double bprice;
    private int bnum;
    private int bcomid;

}
