package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/simplify-path/"
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        final List<String>  dirs = new ArrayList<>();
        for (String dir : path.split("/|//")) {
            if ("".equals(dir)) continue;
            if (".".equals(dir)) continue;
            if ("..".equals(dir)) {
                if (!dirs.isEmpty()) {
                    dirs.remove(dirs.size() - 1);
                }
            } else {
                dirs.add(dir);
            }
        }
        return "/" + String.join("/", dirs);
    }

    @Test
    void t1() {
        verify("/home/", "/home");
    }

    @Test
    void t2() {
        verify("/../", "/");
    }

    @Test
    void t3() {
        verify("/home//foo/", "/home/foo");
    }

    @Test
    void t4() {
        verify("/a/./b/../../c/", "/c");
    }

    private void verify(final String input, final String expected) {
        Assertions.assertEquals(expected, simplifyPath(input));
    }
}
