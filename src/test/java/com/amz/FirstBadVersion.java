package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/first-bad-version/"
 */
public class FirstBadVersion {
    private String versions;

    public int firstBadVersion(int n) {
        int low = 0;
        int high = n;
        int firstBad = -1;
        do {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid - 1;
                firstBad = mid;
            } else {
                low = mid + 1;
            }
        } while (low <= high);
        return firstBad;
    }

    private boolean isBadVersion(int n) {
        return versions.charAt(n) == 'b';
    }

    @Test
    public void t1() {
        check(5, " sssbb", 4);
    }

    @Test
    public void t2() {
        check(1, " b", 1);
    }

    private void check(int count, String versions, int expected) {
        this.versions = versions;
        Assertions.assertEquals(expected, firstBadVersion(count));
    }


}
