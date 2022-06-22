package com.example.shirodemo;

public interface AAA {
    public default void test1() {
        System.out.println("默认方法");
    }

    public static void test2() {
        System.out.println("静态方法");
    }
}
