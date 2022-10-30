package com.mashibing.service;

import com.mashibing.util.LogUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class MyCalculator implements Calculator {
    public Integer add(Integer i, Integer j) throws NoSuchMethodException {
//        Method add = MyCalculator.class.getMethod("add", Integer.class, Integer.class);
//        int[] t=new int[]{i,j};
//        LogUtil.start1(add,new Object[]{i,j});
        Integer result = i+j;
//        LogUtil.stop1(add,new Object[]{i,j});
        return result;
    }

    public Integer sub(Integer i, Integer j) throws NoSuchMethodException {
//        Method sub = MyCalculator.class.getMethod("sub", Integer.class, Integer.class);
//        LogUtil.start(sub,i,j);
        Integer result = i-j;
//        LogUtil.stop(sub,result);
        return result;
    }

    public Integer mul(Integer i, Integer j) throws NoSuchMethodException {
//        Method mul = MyCalculator.class.getMethod("mul", Integer.class, Integer.class);
//        LogUtil.start(mul,i,j);
        Integer result = i*j;
//        LogUtil.stop(mul,result);
        return result;
    }

    public Integer div(Integer i, Integer j) throws NoSuchMethodException {
//        Method div = MyCalculator.class.getMethod("div", Integer.class, Integer.class);
//        LogUtil.start(div,i,j);
        System.out.println("div1");
        Integer result = i/j;
        System.out.println("div2");
//        LogUtil.stop(div,result);
        return result;

    }

    public String show(Integer i){
        System.out.println("show .....");
        return i.toString();
    }
}
