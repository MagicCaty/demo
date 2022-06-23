package com.example.myspring.service;

import com.example.myspring.spring.BeanPostProcessor;
import com.example.myspring.spring.Component;
import com.example.proxy.cglibproxy.CglibProxyFactory;

/**
 * @Description:
 * @author:
 * @createTime: 2022/6/22 16:23
 **/

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if (beanName.equals("orderService")) {
            System.out.println("orderServiceBefore");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if (beanName.equals("orderService")) {
            System.out.println("orderServiceAfter");
        }
        //aop，将实例bean替换成代理对象，并且将原bean作为属性注入代理对象中，解决代理对象无法自动注入的问题
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(bean);
        Object proxyInstance = cglibProxyFactory.getProxyInstance();
        System.out.println("代理对象" + proxyInstance);
        return proxyInstance;
    }
}
