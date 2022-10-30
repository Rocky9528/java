package com.mashibing.controller;

import com.mashibing.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller(value = "teacherControllerx")//手动改bean id名
@Scope(value="prototype")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    public void save(){
        System.out.println("执行TeacherController 的save");
        teacherService.save();

    }
}
