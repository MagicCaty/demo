package com.example.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.annotations.Cacheable;

@SpringBootApplication
@EnableCaching
@MapperScan("com.example.shirodemo.dao")
@ComponentScan(basePackages = {"com.example"})
public class ShirodemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirodemoApplication.class, args);
        System.out.println("http://localhost:8080/swagger-ui/");
    }

}
