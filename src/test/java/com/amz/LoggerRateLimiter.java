package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/logger-rate-limiter/
 */
public class LoggerRateLimiter {

    class Logger {
        private final Map<String, Integer> lastOccured;
        public Logger() {
            lastOccured = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            int prev = lastOccured.getOrDefault(message, Integer.MIN_VALUE);
            if (timestamp - 10 >= prev) {
                lastOccured.put(message, timestamp);
                return true;
            }
            return false;
        }
    }

    @Test
    public void check1() {
        Logger logger = new Logger();
        final List<Boolean> output = Lists.newArrayList((Boolean) null);
        output.add(logger.shouldPrintMessage(1, "foo"));
        output.add(logger.shouldPrintMessage(2, "bar"));
        output.add(logger.shouldPrintMessage(3, "foo"));
        output.add(logger.shouldPrintMessage(8, "bar"));
        output.add(logger.shouldPrintMessage(10, "foo"));
        output.add(logger.shouldPrintMessage(11, "foo"));
        Assertions.assertEquals(Lists.newArrayList(null, true, true, false, false, false, true), output);
    }

}
