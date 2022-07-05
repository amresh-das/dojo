package com.amz;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @see "https://leetcode.com/problems/design-phone-directory/"
 */
public class PhoneDirectory {
    private Set<Integer> availableNumbers;
    private Set<Integer> reservedNumbers;

    public PhoneDirectory(int maxNumbers) {
        availableNumbers = IntStream.range(0, maxNumbers).boxed().collect(Collectors.toSet());
        reservedNumbers = new HashSet<>();
    }

    public int get() {
        Optional<Integer> number = availableNumbers.stream().findAny();
        number.ifPresent(n -> {
            availableNumbers.remove(n);
            reservedNumbers.add(n);
        });
        return number.orElse(-1);
    }

    public boolean check(Integer number) {
        return availableNumbers.contains(number);
    }

    public void release(Integer number) {
        reservedNumbers.remove(number);
        availableNumbers.add(number);
    }

}
