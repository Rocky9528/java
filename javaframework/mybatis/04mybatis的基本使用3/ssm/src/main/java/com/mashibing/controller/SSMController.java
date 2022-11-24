package com.mashibing.controller;

import com.mashibing.bean.Emp;
import com.mashibing.dao.EmpDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class SSMController {

    @Autowired
    EmpDao empDao;

    @RequestMapping("/test")
    public String test(Model model){
        System.out.println("test");

        System.out.println(empDao);
        Emp emp2 = empDao.selectEmpByEmpno(7369);
        System.out.println(emp2);
        model.addAttribute("emp",emp2.getEname());
        return "success";
    }
}
