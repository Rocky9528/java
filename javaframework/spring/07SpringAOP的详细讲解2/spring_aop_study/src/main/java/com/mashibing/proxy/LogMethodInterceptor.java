package com.mashibing.proxy;

import com.mashibing.util.LogUtil;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LogMethodInterceptor implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        boolean printLog = true;
        if (method.getName() == "toString" || method.getName() == "hashCode")
            printLog = false;
        if (printLog)
            LogUtil.start1(method, objects);

        result = methodProxy.invokeSuper(o, objects);

        if (printLog)
            LogUtil.stop1(method, objects);
        ;
        return result;
    }
}
