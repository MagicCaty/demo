package com.example.shirodemo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

//@SpringBootTest
class ShirodemoApplicationTests {

    @Resource
    UserServiceImpl userService;

    @Test
    void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int result = com.compare(2, 1);
        System.out.println(result);
    }

    @Test
    void test2() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
        int result = com.compare(1, 2);
        System.out.println(result);
    }

    @Test
    void test3() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
        int result = com.compare(1, 2);
        System.out.println(result);
    }


    @Test
    public void test5() {
        String str = "{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"操作成功！\",\n" +
                "  \"code\": 200,\n" +
                "  \"result\": {\n" +
                "    \"maintenanceIp\": null,\n" +
                "    \"upgradeTime\": \"2022-02-20 21:48:42\",\n" +
                "    \"idCode\": \"91350200MA356L2T74\",\n" +
                "    \"updateTime\": \"2022-02-20 09:18:03\",\n" +
                "    \"randomCode\": 1100,\n" +
                "    \"enterpriceId\": \"1461620293730668546\",\n" +
                "    \"userId\": null,\n" +
                "    \"license\": \"duQG3SHkyvO9LsKDKGL2Kluv0G9xDTlQMzwuXPk0hnnVqdEM2O4A7KNy9l96p4FLnJFMTo0w3Mrp7BeONySNdPHFL4NSSiolz7cUyWxuneud7GQ4dvH7uaa8H8mjYiWXUHbOP2Ryf63C7EbyEEXVZ7esg5Q6jDiEAzfCHY\",\n" +
                "    \"createTime\": \"2022-02-20 00:48:42\",\n" +
                "    \"platformVersion\": \"1\",\n" +
                "    \"id\": \"1493930333844803585\",\n" +
                "    \"maintenancePort\": null,\n" +
                "    \"enterpriseVersion\": \"1\"\n" +
                "  },\n" +
                "  \"timestamp\": 1645195002222\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        EnterpriseVersion code = jsonObject.getObject("result",EnterpriseVersion.class);


        System.out.println(code);
    }

    @Test
    public void test6() {
        User user = userService.getBaseMapper().selectOne(
                new QueryWrapper<User>().eq("id",null));;
        User user1 = userService.getBaseMapper().selectOne(
                new QueryWrapper<User>().eq("id",""));;
    }

    @Test
    public void test7() {
        User user = userService.getBaseMapper().selectOne(
                new QueryWrapper<User>().eq("id",null));;
        User user1 = userService.getBaseMapper().selectOne(
                new QueryWrapper<User>().eq("id",""));;
    }
    @Test
    public void test8() {
        int i = DateUtil.dayOfMonth(new Date());
        System.out.println(i);
    }

    @Test
    public void test9() {
        ArrayList<Object> objects = null;
        boolean notEmpty = CollectionUtil.isNotEmpty(objects);
        System.out.println(notEmpty);
    }
    @Test
    public void test10() {
        A a = new A();
        System.out.println(A.bigDecimal1);
    }
}

class A{
    static BigDecimal bigDecimal1;
    public void testMethod(String a,B b) {

    }
}
class B{
    private static final String helloword = "test";

}