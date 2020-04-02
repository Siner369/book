package com.siner.dao;


import com.siner.entity.Manager;
import org.springframework.stereotype.Repository;

@Repository("ManagerDao")
public interface ManagerDao {

    //后台检索账号
    public Manager login_admin(Manager manager);
}
