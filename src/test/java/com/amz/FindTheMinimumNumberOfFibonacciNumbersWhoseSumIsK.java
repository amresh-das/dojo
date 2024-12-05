package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK {

    public int findMinFibonacciNumbers(int k) {
        final List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(1);
        fibonacci.add(1);
        while (true) {
            int fibSize = fibonacci.size();
            int next = fibonacci.get(fibSize - 2) + fibonacci.get(fibSize - 1);
            if (next > k) break;
            fibonacci.add(next);
        }

        int steps = 0, remaining = k;
        while (remaining > 0) {
            for (int i = fibonacci.size() - 1; i > 0; i--) {
                int fib = fibonacci.get(i);
                if (remaining >= fib) {
                    remaining -= fib;
                    steps++;
                }
                if (remaining == 0) break;
            }
        }
        return steps;
    }


    @Test
    public void check1() {
        Assertions.assertEquals(2, findMinFibonacciNumbers(7));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, findMinFibonacciNumbers(10));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(3, findMinFibonacciNumbers(19));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(10, findMinFibonacciNumbers(9083494));
    }

}
