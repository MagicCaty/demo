package com.example.proxy.cglibproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @author:
 * @createTime: 2022/6/21 17:07
 **/
@Slf4j
public class TeacherDaoImpl   {

    public void teach() {
        log.info("目标方法调用");
    }
}
