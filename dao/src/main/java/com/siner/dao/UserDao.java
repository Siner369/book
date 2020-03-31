package com.siner.dao;

import com.siner.entity.User;
import org.springframework.stereotype.Repository;


@Repository("UserDao")
public interface UserDao {
    //登录
    public User login(User user);

    // 注册
    public int reg(User user);

    // 注册检测重名
    public String checkRepeat(User str);

    /*
    //加载个人资料
    public int loadInfo(String username);

    //检查信息是否完整  地址 手机
    public int checkComplete(String username);


    //用户修改信息
    public int updateUserInfo(User user);*/

}
