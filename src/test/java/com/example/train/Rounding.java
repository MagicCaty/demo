package com.example.train;

import java.util.function.Function;

public class Rounding {
    public static void main(String[] args) {
        Function rounding = (d) -> {
            int i = (int) ((Double)d + 0.5);
            return i;
        };
        Object apply = rounding.apply(14.19);
        System.out.println(apply);
    }

}
