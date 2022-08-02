package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/asteroid-collision/"
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        final Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            int val = asteroids[i];
            if (val > 0) {
                stack.push(val);
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        stack.push(val);
                        break;
                    }
                    int last = stack.peek();
                    if (last < 0) {
                        stack.push(val);
                        break;
                    } else if (last == -val) {
                        stack.pop();
                        break;
                    } else if (last < -val) {
                        stack.pop();
                    } else {
                        break;
                    }
                }
            }
        }
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void check1() {
        int[] input = Utils.stringToIntArray("[5,10,-5]");
        String expected = "[5,10]";
        Assertions.assertEquals(expected, Utils.toString(asteroidCollision(input)));
    }

    @Test
    public void check2() {
        int[] input = Utils.stringToIntArray("[8,-8]");
        String expected = "[]";
        Assertions.assertEquals(expected, Utils.toString(asteroidCollision(input)));
    }

    @Test
    public void check3() {
        int[] input = Utils.stringToIntArray("[10,2,-5]");
        String expected = "[10]";
        Assertions.assertEquals(expected, Utils.toString(asteroidCollision(input)));
    }

    @Test
    public void check4() {
        int[] input = Utils.stringToIntArray("[10,2,-1,-3]");
        String expected = "[10]";
        Assertions.assertEquals(expected, Utils.toString(asteroidCollision(input)));
    }

}
