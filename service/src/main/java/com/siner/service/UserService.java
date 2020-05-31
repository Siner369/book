package com.siner.service;

import com.siner.entity.User;

import java.util.List;

public interface UserService {
    //登录
    User login(User user);

    //注册
    boolean reg(User user);

    //查找同名
    boolean checkDouble(User user);

    //加载个人资料
    User loadInfo(String username);

    //更新资料
    int updateUserInfo(User user);

    List<User> allUser();

    int delUser(int uid);

    int LockUser(int uid);

    int UnLockUser(int uid);
}
