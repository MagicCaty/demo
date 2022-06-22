package com.example.proxy.cglibproxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: 无需接口的CgLib动态代理，工厂需要实现MethodInterceptor，使用cglib包下的API
 * @author:
 * @createTime: 2022/6/21 17:10
 **/
@Slf4j
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回代理对象
     */
    public Object getProxyInstance() {
       //1.创建工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回调函数
        enhancer.setCallback((Callback) this);
        //4.创建子类，即代理对象
        return enhancer.create();
    }

    /*
    类似invoke
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib调用方法before");
        Object invoke = method.invoke(target, args);
        System.out.println("cglib调用方法after");
        return invoke;
    }
}
