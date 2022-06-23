package com.example.myspring.service;

import com.example.myspring.spring.BeanName;
import com.example.myspring.spring.Component;
import lombok.Data;

/**
 * @Description:
 * @author:
 * @createTime: 2022/6/22 15:28
 **/
@Component
@Data
public class OrderService implements BeanName {
//   @Autowired
//    private UserService userService;

   private String beanName;

}
