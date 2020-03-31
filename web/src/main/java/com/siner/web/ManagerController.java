package com.siner.web;


import com.siner.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("ManagerController")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

}
