package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReduceToZeroEvenOdd {
    public int numberOfSteps(int num) {
        if (num == 0) return 0;
        if (num == 1) return 1;
        return (num % 2 + 1) + numberOfSteps(num / 2);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(4, numberOfSteps(8));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(6, numberOfSteps(14));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(12, numberOfSteps(123));
    }
}
