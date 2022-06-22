package com.example.proxy.staticproxy;

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
        TeacherDao proxyInstance = (TeacherDao) new TeacherProxyFactory(teacherDao);
        proxyInstance.teach();
    }
}
