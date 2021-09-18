package com.amz;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    public static int[] stringToIntArray(final String input) {
        return Arrays.stream(input.replaceAll("[\\[\\]]", "").split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public static String[] toStringArray(final String input) {
        return input.replaceAll("[\\[\\]]", "").split(",");
    }

    public static String listOfIntegerListToString(final List<List<Integer>> output) {
        return "[" + output.stream().map(i -> "[" + i.stream().map(Object::toString).collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + "]";
    }

    public static int[][] to2dIntMatrix(final String input) {
        if (!input.matches(".*\\d+.*")) return new int[0][0];
        final String[] rows = input.substring(1, input.length() - 2).split("\\],\\[");
        final int colLen = rows[0].split("\\],\\[").length;
        final int[][] out = new int[rows.length][colLen];
        int i = 0;
        for (String row : rows) {
            out[i++] = stringToIntArray(row);
        }
        return out;
    }

    public static String[][] to2dStringMatrix(final String input) {
        final String[] rows = input.substring(1, input.length() - 2).split("\\],\\[");
        final int colLen = rows[0].split("\\],\\[").length;
        final String[][] out = new String[rows.length][colLen];
        int i = 0;
        for (String row : rows) {
            out[i++] = toStringArray(row);
        }
        return out;
    }

    public static String from2dIntMatrix(final int[][] input) {
        return "[" + Arrays.stream(input).map(i -> "[" + Arrays.stream(i).boxed().map(Objects::toString).collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + "]";
    }

    public static String from2dMatrix(final Object[][] input) {
        return "[" + Arrays.stream(input).map(i -> "[" + Arrays.stream(i).map(Objects::toString).collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + "]";
    }

    public static String from2dMatrix(final List<List<String>> input) {
        return "[" + input.stream().map(i -> "[" + i.stream().map(Objects::toString).collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + "]";
    }
}
