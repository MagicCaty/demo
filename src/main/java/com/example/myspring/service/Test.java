package com.example.myspring.service;

import com.example.myspring.spring.MyApplicationContext;

/**
 * @Description:
 * @author:
 * @createTime: 2022/6/22 10:33
 **/
public class Test {
    public static void main(String[] args) {
//        SpringApplication.run(. class,args);
        MyApplicationContext myApplicationContext = new MyApplicationContext(AppConfig.class);
        OrderService orderService = (OrderService)  myApplicationContext.getBean("orderService");

    }


}
