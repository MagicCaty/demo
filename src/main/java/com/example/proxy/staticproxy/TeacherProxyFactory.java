package com.example.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:  静态代理，写死代理工厂所实现的接口，因此只能代理实现接口的子类
 * @author:
 * @createTime: 2022/6/21 17:10
 **/
@Slf4j
public class TeacherProxyFactory implements TeacherDao{
    private TeacherDao target;

    public TeacherProxyFactory(TeacherDao target) {
        this.target = target;
    }

    /**
     * 写死，间接调用真正的目标方法
     */
    public void teach() {
        System.out.println("代理对象在增强方法调用before");
        target.teach();
        System.out.println("代理对象在增强方法调用after");
    }
}
