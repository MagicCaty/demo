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
//        orderService.testAutowired();
//        System.out.println(orderService.getBeanName());
//        UserService userService = (UserService)  myApplicationContext.getBean("userService");
//        UserService userService1 = (UserService) myApplicationContext.getBean("userService");
//        UserService userService2 = (UserService) myApplicationContext.getBean("userService");
//        System.out.println(userService);
//        System.out.println(userService1);
//        System.out.println(userService2);


    }

    public static void testst() {
        throw new RuntimeException("嘀嘀嘀");
    }
}
