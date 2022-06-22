package com.example.shirodemo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shirodemo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-15 21:06:15
 */
public interface UserDao extends BaseMapper<User> {
    int queryTestO(@Param("password") String password,@Param("year") String year,@Param("month") String month,@Param("day")String day);

    List<User> getUserList();
}
