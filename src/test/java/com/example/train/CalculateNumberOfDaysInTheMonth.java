package com.example.train;

import java.time.LocalDate;

public class CalculateNumberOfDaysInTheMonth {
    public static void main(String[] args) {
            method(2022);
    }

    public static void method(int year) {
        for (int i = 1; i < 12; i++) {
            System.out.println(year + "年" + i + "月" + LocalDate.of(year,i,1).lengthOfMonth() + "天");
        }
    }
}
