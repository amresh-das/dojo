package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/asteroid-collision/?envType=study-plan-v2&envId=leetcode-75"
 */
public class AsteroidCollisionTest {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            final int a = asteroids[i];
            if (stack.isEmpty()) {
                stack.push(a);
            } else {
                int last = stack.peek();
                if (a < 0 && last > 0) {
                    if (last == -a) {
                        stack.pop();
                    } else if (last < -a) {
                        stack.pop();
                        i--;
                    }
                } else {
                    stack.push(a);
                }
            }
        }
        int[] output = new int[stack.size()];
        int n = stack.size();
        while (!stack.isEmpty()) {
            output[--n] = stack.pop();
        }
        return output;
    }

    @Test
    void check1() {
        Assertions.assertEquals(List.of(5,10), Arrays.stream(asteroidCollision(new int[] {5,10,-5})).boxed().toList());
    }

    @Test
    void check2() {
        Assertions.assertEquals(List.of(), Arrays.stream(asteroidCollision(new int[] {8,-8})).boxed().toList());
    }

    @Test
    void check3() {
        Assertions.assertEquals(List.of(10), Arrays.stream(asteroidCollision(new int[] {10,2,-5})).boxed().toList());
    }

    @Test
    void check4() {
        Assertions.assertEquals(List.of(-2,-1,1,2), Arrays.stream(asteroidCollision(new int[] {-2,-1,1,2})).boxed().toList());
    }
}
