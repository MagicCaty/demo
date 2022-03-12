package com.example.shirodemo.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-02-15 21:06:21
 */
@RestController
@RequestMapping()
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/test")
    public User selectOne(String id) {
        User entity = userService.lambdaQuery().eq(User::getId,id).one();
        return entity;
    }

    @GetMapping("/queryById")
    public User queryById(String id) {
        User user = userService.queryTest(id);
        return user;
    }

    @GetMapping("/")
    public String index() {
        return "hello";
    }

}