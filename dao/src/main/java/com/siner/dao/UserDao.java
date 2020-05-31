package com.siner.dao;

import com.siner.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("UserDao")
public interface UserDao {
    //登录
    public User login(User user);

    // 注册
    public int reg(User user);

    // 注册检测重名
    public String checkRepeat(User str);


    //加载个人资料
    public User loadInfo(String username);

    //用户修改信息
    public int updateUserInfo(User user);

    List<User> allUser();

    int delUser(int uid);

    int lockUser(int uid);

    int unlockUser(int uid);
}
