package com.example.shirodemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyAop {

    //指定注解MyInterface
    @Around("@annotation(com.example.shirodemo.diyinterface.MyInterface)")
    public Object improveTest(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("执行前");
        //执行目标方法
        Object proceed = joinPoint.proceed();
        System.out.println("执行后");
        return (Integer)proceed + 9;
    }
}
