package com.example.redisdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.redisdemo.entity.SysUser;
import com.example.redisdemo.service.UserService;
import com.example.redisdemo.utils.RedisOperator;
import com.example.redisdemo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @Autowired
    private RedisOperator redis;

    @Resource
    private UserService userService;



//    @RequestMapping("/test")
//    public Result test() {
//        SysUser user = new SysUser();
//        user.setId("100111");
//        user.setUsername("spring boot");
//        user.setPassword("abc123");
//        user.setIsDelete(0);
//        user.setRegistTime(new Date());
//        strRedis.opsForValue().set("json:user", JSON.toJSONString(user));
//        return Result.ok(user);
//    }

//    @RequestMapping("/getJsonList")
//    public Result getJsonList() {
//
//        SysUser user = new SysUser();
//        user.setAge(18);
//        user.setName("慕课网");
//        user.setPassword("123456");
//        user.setBirthday(new Date());
//
//        SysUser u1 = new SysUser();
//        u1.setAge(19);
//        u1.setName("spring boot");
//        u1.setPassword("123456");
//        u1.setBirthday(new Date());
//
//        SysUser u2 = new SysUser();
//        u2.setAge(17);
//        u2.setName("hello spring boot");
//        u2.setPassword("123456");
//        u2.setBirthday(new Date());
//
//        List<SysUser> userList = new ArrayList<SysUser>();
//        userList.add(user);
//        userList.add(u1);
//        userList.add(u2);
//
//        redis.set("json:info:userlist", JSON.toJSONString(user), 2000);
//
//        String userListJson = redis.get("json:info:userlist");
//        List<SysUser> userListBorn = JsonUtils.jsonToList(userListJson, SysUser.class);
//
//        return Result.ok(userListBorn);
//    }
}

