package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/time-needed-to-inform-all-employees/"
 */
public class TimeNeededToInformAllEmployees {

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        final Map<Integer, List<Integer>> managerEmployees = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int emp = i;
            managerEmployees.compute(manager[i], (k, v) -> {
                List<Integer> emps = v == null ? new ArrayList<>() : v;
                emps.add(emp);
                return emps;
            });
        }
        return inform(managerEmployees, informTime, headID, 0);
    }

    private int inform(final Map<Integer, List<Integer>> managerEmployees, final int[] informTime, final int head, final int time) {
        if (managerEmployees.containsKey(head)) {
            int t = -1;
            for (int e : managerEmployees.get(head)) {
                t = Math.max(inform(managerEmployees, informTime, e, time + informTime[head]), t);
            }
            return t;
        }
        return time;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(0, numOfMinutes(1, 0, new int[] {-1}, new int[] {0}));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, numOfMinutes(6, 2, new int[] {2,2,-1,2,2,2}, new int[] {0,0,1,0,0,0}));
    }
}
