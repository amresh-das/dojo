package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        final StringBuilder result = new StringBuilder();
        final int[] solution = new int[num1.length() + num2.length() - 1];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                solution[i + j] += a * b;
            }
        }
        for (int i = solution.length - 1; i >= 1; i--) {
            solution[i - 1] += solution[i] /10;
            solution[i] = solution[i] % 10;
        }
        for (int d : solution) {
            result.append(d);
        }
        return result.toString();
    }

    @Test
    void t1() {
        Assertions.assertEquals("6", multiply("2", "3"));
    }

    @Test
    void t2() {
        Assertions.assertEquals("56088", multiply("123", "456"));
    }
}
