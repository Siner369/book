package com.siner.service;

import com.siner.entity.User;

public interface UserService {
    //登录
    public User login(User user);

    //注册
    public boolean reg(User user);

    //查找同名
    public boolean checkDouble(User user);

    //加载个人资料
    public User loadInfo(String username);

    //更新资料
    public int updateUserInfo(User user);

}
