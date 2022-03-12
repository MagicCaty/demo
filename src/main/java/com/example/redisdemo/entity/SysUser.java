package com.example.redisdemo.entity;


import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private String id;
    private String name;
    private String username;
    private String password;
    private Integer isDelete;
    private Integer age;
    private Date registTime;
    private Date birthday;
}
