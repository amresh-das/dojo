package com.amz;

import com.amz.leet.ListNode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    public static int[] stringToIntArray(final String input) {
        final String[] inputs = input.replaceAll("[\\[\\]]", "").split(",");
        return inputs.length == 0 || (inputs.length == 1 && inputs[0].equals("")) ? new int[0] : Arrays.stream(inputs).mapToInt(Integer::parseInt).toArray();
    }

    public static char[] stringToCharArray(final String input) {
        final String[] inputs = input.replaceAll("[\"]", "").split(",");
        if (inputs.length == 0 || (inputs.length == 1 && inputs[0].equals(""))) return new char[0];
        char[] chars = new char[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            chars[i] = inputs[i].charAt(0);
        }
        return chars;
    }

    public static String[] toStringArray(final String input) {
        return input.replaceAll("[\\[\\]\"]", "").split(",");
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

    public static char[][] to2dCharMatrix(final String input) {
        final String[] rows = input.substring(2, input.length() - 2).split("\\],\\[");
        final int colLen = rows[0].split(",").length;
        final char[][] out = new char[rows.length][colLen];
        int i = 0;
        for (String row : rows) {
            out[i++] = stringToCharArray(row);
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

    public static ListNode toListNode(final String input) {
        ListNode head = null;
        ListNode prev = null;
        for (final int t : stringToIntArray(input)) {
            final ListNode curr = new ListNode(t);
            if (head == null) {
                head = curr;
            } else {
                prev.next = curr;
            }
            prev = curr;
        }
        return head;
    }

    public static String toString(final int[] input) {
        final StringBuilder s = new StringBuilder("[");
        for (int t : input) {
            s.append(t);
            s.append(",");
        }
        if (s.length() > 1) {
            s.deleteCharAt(s.length() - 1);
        }
        s.append("]");
        return s.toString();

    }

    public static String toString(final int[][] input) {
        final StringBuilder s = new StringBuilder("[");
        for (int[] t : input) {
            s.append(toString(t));
            s.append(",");
        }
        if (s.length() > 1) {
            s.deleteCharAt(s.length() - 1);
        }
        s.append("]");
        return s.toString();
    }

    public static String toString(final ListNode input) {
        ListNode curr = input;
        final StringBuilder s = new StringBuilder("[");
        while (curr != null) {
            s.append(curr.val);
            s.append(',');
            curr = curr.next;
        }
        if (s.length() > 1) {
            s.deleteCharAt(s.length() - 1);
        }
        s.append("]");
        return s.toString();
    }
}
