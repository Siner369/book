package com.siner.service.impl;


import com.siner.dao.UserDao;
import com.siner.entity.User;
import com.siner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) {
        System.out.println("开始业务逻辑层的调用（登录）....");
        System.out.println(user);
        User result=userDao.login(user);

        if(result!=null){
            return result;
        }else {
            return null;
        }
    }

    @Override
    public User loadInfo(String username) {
        System.out.println("开始业务逻辑层的调用（加载信息）....");
        System.out.println(username);
        User result=userDao.loadInfo(username);

        if(result!=null){
            return result;
        }else {
            return null;
        }
    }

    @Override
    public int updateUserInfo(User user) {
        System.out.println("开始业务逻辑层的调用（更新信息）....");
        System.out.println(user);
        int n = userDao.updateUserInfo(user);
        System.out.println("改变了吗："+n);
        return n;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public boolean reg(User user) {
        System.out.println("开始业务逻辑层的调用（注册）....");
        System.out.println(user);
        int n=userDao.reg(user);

        if(n>0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkDouble(User user) {
        System.out.println("Service开始检测"+user.getUname()+"是否重名....");

        String s = userDao.checkRepeat(user);
        if(s!=null && s.length()>0) {
            System.out.println("service找到了重复的名字:"+s);
            return false;
        }else {
            return true;
        }
    }

}
