package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/can-place-flowers/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class CanPlaceFlowersTest {

    @Test
    public void check1() {
        Assertions.assertTrue(canPlaceFlowers(new int[] {1,0,0,0,1}, 1));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(canPlaceFlowers(new int[] {1,0,0,0,1}, 2));
    }

    @Test
    public void check3() {
        Assertions.assertFalse(canPlaceFlowers(new int[] {1,0,1,0,1,0,1}, 1));
    }

    @Test
    public void check4() {
        Assertions.assertTrue(canPlaceFlowers(new int[] {0}, 1));
    }

    @Test
    public void check5() {
        Assertions.assertFalse(canPlaceFlowers(new int[] {1}, 1));
    }

    @Test
    public void check6() {
        Assertions.assertTrue(canPlaceFlowers(new int[] {0, 0}, 1));
    }

    @Test
    public void check7() {
        Assertions.assertFalse(canPlaceFlowers(new int[] {1, 0}, 1));
    }

    @Test
    public void check8() {
        Assertions.assertFalse(canPlaceFlowers(new int[] {0, 1}, 1));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int start = 1;
        int end = flowerbed.length - 2;
        if (n == 0) return true;
        if (flowerbed.length == 0) return false;
        if (flowerbed.length == 1) return n == 1 && flowerbed[0] == 0;
        if (flowerbed.length == 2) return n == 1 && flowerbed[0] == 0 && flowerbed[1] == 0;
        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            flowerbed[0] = 1;
            n--;
        }
        if (flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
            flowerbed[flowerbed.length - 1] = 1;
            n--;
        }
        while (start <= end) {
            if (flowerbed[start] == 0 && flowerbed[start - 1] == 0 && flowerbed[start + 1] == 0) {
                flowerbed[start] = 1;
                n--;
            }
            start++;
            if (flowerbed[end] == 0 && flowerbed[end - 1] == 0 && flowerbed[end + 1] == 0) {
                flowerbed[end] = 1;
                n--;
            }
            if (n <= 0) break;
            end--;
        }
        return n <= 0;
    }
}
