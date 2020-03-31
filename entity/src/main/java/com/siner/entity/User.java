package com.siner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int uid;//用户ID
    private String uname;//账号
    private String upass;//密码
    private String ugender;//性别
    private String uaddress;//地址
    private String uemail;// 邮箱
    private int umailcode;//邮编
    private String utel;//手机号
    private int ustate;//状态 '账户状态 0是正常 1只能浏览 2直接冻结'

}
