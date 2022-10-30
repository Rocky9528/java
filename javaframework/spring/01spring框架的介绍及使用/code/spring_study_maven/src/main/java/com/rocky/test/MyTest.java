package com.rocky.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.rocky.bean.Person;
import com.rocky.factory.MyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");

//        Object person1 = context.getBean("person1");
//        System.out.println(person1);

//        Person person3 = context.getBean("person3", Person.class);
//        System.out.println(person3);

//        Person person5_1 = context.getBean("person5", Person.class);
//
//        Person person5_2 = context.getBean("person5", Person.class);
//
//        System.out.println(person5_1);
//        System.out.println(person5_2);
//        System.out.println("person5_1==person5_2 :" + (person5_1 == person5_2));
//
//        System.out.println("person5_1.equals(person5_2) :" + person5_1.equals(person5_2));
//
//        //parent是抽象类，无法实例化
//        Person parent = context.getBean("parent", Person.class);
//        System.out.println(parent);

//        Person person6 = context.getBean("person6", Person.class);
//        System.out.println(person6);
//
//        Person person7 = context.getBean("person7", Person.class);
//        System.out.println(person7);
//
//        Object person8_1 = context.getBean("person8");
//
//        Object person8_2 = context.getBean("person8");
//
//        System.out.println(person8_1);
//        System.out.println(person8_2);
//
//        System.out.println("person8_1==person8_2 :" + (person8_1 == person8_2));
//
//        System.out.println("person8_1.equals(person8_2) :" + person8_1.equals(person8_2));
//
//        Object constant1 = context.getBean("constant1");
//        System.out.println(constant1);
//
//        Object propertypath = context.getBean("propertypath");
//        System.out.println(propertypath);
//
//        Object properties = context.getBean("properties1");
//        System.out.println(properties);
//
//        Object person9 = context.getBean("person9");
//        System.out.println(person9);
//
//        Object person10 = context.getBean("person10");
//        System.out.println(person10);
//
//        Object myfactorybean = context.getBean("myfactorybean");
//        System.out.println(myfactorybean);

//        DruidDataSource dataSource1 = context.getBean("dataSource1", DruidDataSource.class);
//        System.out.println(dataSource1.getConnection());
//
//        DruidDataSource dataSource2 = context.getBean("dataSource2", DruidDataSource.class);
//        System.out.println(dataSource2.getConnection());

//        Object person11 = context.getBean("person11");
//        System.out.println(person11);

        Object person12 = context.getBean("person12");
        System.out.println(person12);

        ((AbstractApplicationContext)context).close();

    }

}