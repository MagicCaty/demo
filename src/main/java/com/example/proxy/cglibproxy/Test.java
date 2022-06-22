package com.example.proxy.cglibproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @author:
 * @createTime: 2022/6/21 17:15
 **/
@Slf4j
public  class Test {
    public static void main(String[] args) {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(teacherDao);
        TeacherDaoImpl proxyInstance = (TeacherDaoImpl) cglibProxyFactory.getProxyInstance();
        proxyInstance.teach();
    }
}
