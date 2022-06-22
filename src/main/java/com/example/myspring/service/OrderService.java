package com.example.myspring.service;

import com.example.myspring.spring.Autowired;
import com.example.myspring.spring.BeanName;
import com.example.myspring.spring.Component;

/**
 * @Description:
 * @author:
 * @createTime: 2022/6/22 15:28
 **/
@Component
public class OrderService implements BeanName {
   @Autowired
    private UserService userService;

   private String beanName;

   public void testAutowired() {
       System.out.println(userService);
   }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getBeanName() {
        return beanName;
    }
}
