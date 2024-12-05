package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/binary-trees-with-factors/"
 */
public class BinaryTreesWithFactors {

    public int numFactoredBinaryTrees(int[] arr) {
        final List<Integer> nums = Arrays.stream(arr).boxed().sorted().collect(Collectors.toList());
        final Map<Integer, BigInteger> roots = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            BigInteger count = BigInteger.ONE;
            int num = nums.get(i);
            int factor = i;
            for (int j = 0; j < Math.min(factor, i); j++) {
                factor = nums.get(j);
                if (num % factor == 0 && roots.containsKey(num / factor)) {
                    count = count.add(roots.get(factor).multiply(roots.getOrDefault(num / factor, BigInteger.ONE)));
                }
            }
            roots.put(num, count);
        }
        return roots.values().stream().reduce(BigInteger.ZERO, BigInteger::add).mod(new BigInteger("1000000007")).intValue();
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, numFactoredBinaryTrees(Utils.stringToIntArray("[2,4]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(7, numFactoredBinaryTrees(Utils.stringToIntArray("[2,4,5,10]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(12, numFactoredBinaryTrees(Utils.stringToIntArray("[18,3,6,2]")));
    }

}
