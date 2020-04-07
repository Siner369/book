package com.siner.service.impl;


import com.siner.dao.ManagerDao;
import com.siner.entity.Manager;
import com.siner.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ManagerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public Manager login_admin(String mname, String mpass) {
        System.out.println("开始业务逻辑层的调用（登录）....");
        System.out.println(mname+","+mpass);
        Manager result=managerDao.login_admin(mname,mpass);
        System.out.println(result);
        if(result!=null){
            System.out.println("有数据 登录成功");
            return result;

        }else {
            return null;
        }

    }
}
