package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/unique-paths/"
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        long num=1;
        for (int i=Math.max(m-1,n-1)+1;i<m+n-1;i++){
            num*=i;
        }
        for(int i=1;i<Math.min(m-1,n-1)+1;i++){
            num/=i;
        }
        return (int)num;
    }

    @Test
    void t1() {
        Assertions.assertEquals(28, uniquePaths(7, 3));
    }

    @Test
    void t2() {
        Assertions.assertEquals(6, uniquePaths(3, 3));
    }

    @Test
    void t3() {
        Assertions.assertEquals(6, uniquePaths(3, 3));
    }

    @Test
    void t4() {
        Assertions.assertEquals(3, uniquePaths(3, 2));
    }

    @Test
    void t5() {
        Assertions.assertEquals(28, uniquePaths(3, 7));
    }

    @Test
    void t6() {
        Assertions.assertEquals(48620, uniquePaths(10, 10));
    }
}
