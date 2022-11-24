package com.rocky.controller;

import com.rocky.bean.Emp;
import com.rocky.dao.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SSMController {

    @Autowired
    EmpMapper empMapper;

    @RequestMapping("/test")
    public String test(Model model){
        System.out.println("test");
        Emp emp = empMapper.selectByPrimaryKey(7369);
        model.addAttribute("empno",emp.getEmpno());
        model.addAttribute("ename",emp.getEname());
        System.out.println(emp);

        return "success";
    }

}
