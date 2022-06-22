package com.example.myspring.spring;

/**
 * @Description: bean的定义,baen的类型、单例/多例、是否为懒加载。。。
 * @author:
 * @createTime: 2022/6/22 11:20
 **/
public class BeanDefinition {
    //类的类型
    private Class<?> type;
    //类为单例或多例
    private String scope;

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
