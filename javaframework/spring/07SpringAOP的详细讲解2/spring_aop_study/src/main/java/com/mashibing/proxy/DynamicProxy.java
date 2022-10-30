package com.mashibing.proxy;

import com.mashibing.service.Calculator;
import com.mashibing.util.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy<T> implements InvocationHandler {

    T _type;

    public DynamicProxy() {
    }

    public DynamicProxy(T type) {
        this._type = type;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object o =null;

        try{
            LogUtil.start1(method,args);

            o=method.invoke(_type, args);

            LogUtil.stop1(method,args);
        }catch (Exception e){
            LogUtil.logException1(method,e);
        }finally {
            LogUtil.logFinally1(method,args);
        }





        return o;
    }


}
