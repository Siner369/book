package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Manager {
    private int mid;//管理员ID
    private String mname;//后台账号
    private String mpass;//密码
}
