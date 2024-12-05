package com.amz;

/**
 * @see "https://leetcode.com/problems/guess-number-higher-or-lower/?envType=study-plan-v2&envId=leetcode-75"
 */
public class GuessNumberHigherOrLowerTest {

    public int guessNumber(int n) {
        int l = 0, r = n;
        while(true) {
            var guessed = l + (r - l) / 2;
            var res = guess(guessed);
            if (res == 0) return guessed;
            else if (res == 1) {
                l = guessed + 1;
            } else {
                r = guessed - 1;
            }
        }
    }

    public int guess(int n) {
        return 0;
    }

}
