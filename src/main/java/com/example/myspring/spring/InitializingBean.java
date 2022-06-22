package com.example.myspring.spring;

/**
 * @Description:  实现此接口，将进行初始化
 * @author:
 * @createTime: 2022/6/22 16:03
 **/
public interface InitializingBean {
    public void afterPropertiesSet();
}
