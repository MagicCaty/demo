package com.example.redisdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redisdemo.entity.User;
import com.example.redisdemo.service.UserService;
import com.example.redisdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Ma
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-03-12 12:56:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




