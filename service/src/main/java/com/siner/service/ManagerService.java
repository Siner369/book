package com.siner.service;

import com.siner.entity.Manager;
import org.apache.ibatis.annotations.Param;

public interface ManagerService {
    public Manager login_admin(String mname ,String mpass);
}
