package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private int cid;//图书评价ID
    private int uid;//用户ID
    private int oid;//订单ID
    private int cscore;//评分
    private String comments;//评论内容
}
