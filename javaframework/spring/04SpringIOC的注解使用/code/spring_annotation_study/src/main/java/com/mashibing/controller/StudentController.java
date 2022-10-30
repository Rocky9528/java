package com.mashibing.controller;

import com.mashibing.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    public  void save(){
        System.out.println("执行StudentController 的save");
        studentService.save();

    }

}
