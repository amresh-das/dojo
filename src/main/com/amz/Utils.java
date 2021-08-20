package com.amz;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static int[] stringToIntArray(final String input) {
        return Arrays.stream(input.replaceAll("[\\[\\]]", "").split(",")).mapToInt(Integer::parseInt).toArray();
    }


    public static String listOfIntegerListToString(final List<List<Integer>> output) {
        return "[" + output.stream().map(i -> "[" + i.stream().map(Object::toString).collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + "]";
    }
}
