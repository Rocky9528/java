package com.rocky.test;

import com.rocky.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");

//        Person person3 = context.getBean("person3", Person.class);
//        System.out.println(person3);

//        Person person5 = context.getBean("person5", Person.class);
//        System.out.println(person5);
//
//        Person person6 = context.getBean("person6", Person.class);
//        System.out.println(person6);

//        //person7是抽象类，无法实例化
//        Person person7 = context.getBean("person7", Person.class);
//        System.out.println(person7);

        Person person8 = context.getBean("person8", Person.class);
        System.out.println(person8);
//
//
//        Object constant1 = context.getBean("constant1");
//        System.out.println(constant1);
//
//        Object propertypath = context.getBean("propertypath");
//        System.out.println(propertypath);
//
//        Object properties = context.getBean("properties1");
//        System.out.println(properties);


    }

}