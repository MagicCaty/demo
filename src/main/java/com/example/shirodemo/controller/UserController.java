package com.example.shirodemo.controller;

import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-02-15 21:06:21
 */
@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;

    private final RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    public UserController(UserService userService,RedisTemplate<Object,Object> redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }


    @GetMapping("/aoptest")
    public Integer testAop() {
        Integer test = userService.test();
        System.out.println(test);
        return test;
    }

    @GetMapping("/test")
    public User selectOne(String id) {
        User entity = userService
                .lambdaQuery()
                .eq(User::getId,id)
                .one();
        return entity;
    }

    @GetMapping("/queryById")
    @Cacheable(value = "user",key = "#id")
    public User queryById(String id) {
        User user = userService.getById(id);
        return user;
    }

    @GetMapping("/testSendNull")
    public String testSendNull() {
        User user = new User();
        redisTemplate.opsForValue().set("testSendNull",user);
        return "success!";
    }
    @GetMapping("/getSendNull")
    public String getSendNull()
    {
        Object testSendNull = redisTemplate.opsForValue().get("testSendNull");
        System.out.println(testSendNull.getClass().getName());
        return "success!";
    }

}