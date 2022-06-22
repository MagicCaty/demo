package com.example.proxy.dynamicproxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 基于接口的动态代理，使用了反射机制以及Proxy.newProxyInstance方法，可以动态的适配所有实现过接口的子类
 * @author:
 * @createTime: 2022/6/21 17:10
 **/
@Slf4j
public class TeacherProxyFactory {
    private Object target;

    public TeacherProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回代理对象
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        log.info("代理方法before");
                        Object invoke = method.invoke(target, args);
                        log.info("代理方法after");
                        return invoke;
                    }
                });

    }
}
