package com.siner.dao;


import com.siner.entity.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ManagerDao")
public interface ManagerDao {
    //后台检索账号
    public Manager login_admin(@Param("name") String mname, @Param("pass") String mpass);
}
