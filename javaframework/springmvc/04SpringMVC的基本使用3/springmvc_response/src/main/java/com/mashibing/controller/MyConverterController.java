package com.mashibing.controller;

import com.mashibing.bean.Person;
import com.mashibing.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义类型转换器的时候一定要注意对应的属性值 跟方法中参数的值要对应起来
 *
 */
@Controller
public class MyConverterController {

    @RequestMapping("/convertertest")
    public String testConverter(String user, Model model){
        System.out.println(user);
        model.addAttribute("user",user);
        return "success";
    }

    @RequestMapping("/converter")
    public String testConverter(@RequestParam("user") User user1, Model model){
        System.out.println(user1);
        model.addAttribute("user",user1);
        return "success";
    }

    @RequestMapping("/converter2")
    public String testConverter(@RequestParam("person") Person person1, Model model){
        System.out.println(person1);
        model.addAttribute("person",person1);
        return "success";
    }

}
