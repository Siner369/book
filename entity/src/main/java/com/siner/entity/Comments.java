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
    private String uname;//用户名
    private int bid;//书籍ID
    private boolean isbuy; // 买了吗
    private int cscore;//评分
    private String comments;//评论内容
    private Book book;
}
