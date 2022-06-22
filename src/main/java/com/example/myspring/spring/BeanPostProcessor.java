package com.example.myspring.spring;

/**
 * @Description: 实现此接口，控制bean初始化前，初始化后操作
 * @author:
 * @createTime: 2022/6/22 16:11
 **/
public interface BeanPostProcessor{
    public Object postProcessBeforeInitialization(String beanName, Object bean);

    public Object postProcessAfterInitialization(String beanName, Object bean);

}
