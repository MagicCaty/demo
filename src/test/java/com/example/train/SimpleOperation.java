package com.example.train;

public class SimpleOperation {
    public static void main(String[] args) {
        SimpleOperation simpleOperation = new SimpleOperation();
        System.out.println(simpleOperation.calculate(10, 5));
    }

    public String calculate(int i1, int i2) {
        if (i1 < 0 || i2 < 0 || i1 % 1 != 0 || i2 % 1 != 0) {
            System.out.println("参数不为正整数");
        }

        String result;
        result = i1 + i2 + " ";
        if (i1 > i2) {
            result = result + (i1 - i2) + " ";
        } else {
            result = result + (i2 - i1) + " ";
        }
        result = result + (i1 * i2) + " ";
        if (i1 > i2) {
            result =result + ( i1 / i2 ) + " ";
        } else {
            result = result + (i2 / i1 )+ " ";
        }
        if (i1 > i2) {
            result = result + (i1 % i2) + " ";
        } else {
            result = result + (i2 % i1) + " ";
        }
        return result;
    }
}
