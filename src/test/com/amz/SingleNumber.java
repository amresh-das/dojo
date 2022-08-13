package com.amz;

import java.util.HashSet;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/single-number/"
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int num = 0;
        for (int n: nums) {
            num ^= n;
        }
        return num;
    }

    public int singleNumber2(int[] nums) {
        final Set<Integer> found = new HashSet<>();
        for (int n: nums) {
            if (found.contains(n)) {
                found.remove(n);
            } else {
                found.add(n);
            }
        }
        return found.stream().findFirst().get();
    }

}
