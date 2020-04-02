package com.siner.service.impl;


import com.siner.dao.ManagerDao;
import com.siner.entity.Manager;
import com.siner.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public Manager login_admin(Manager manager) {
        System.out.println("开始业务逻辑层的调用（登录）....");
        System.out.println(manager);
        Manager result=managerDao.login_admin(manager);

        if(result!=null){
            return result;
        }else {
            return null;
        }

    }
}
