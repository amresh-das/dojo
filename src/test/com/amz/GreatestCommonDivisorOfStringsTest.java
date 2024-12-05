package com.amz;

/**
 * @see "https://leetcode.com/problems/greatest-common-divisor-of-strings/?envType=study-plan-v2&envId=leetcode-75"
 */
public class GreatestCommonDivisorOfStringsTest {

    public String gcdOfStrings(String str1, String str2) {
        for (int divisorLen = Math.min(str1.length(), str2.length()); divisorLen > 0; divisorLen--) {
            if (str1.length() % divisorLen != 0 || str2.length() % divisorLen != 0) continue;
            String divisor = str1.substring(0, divisorLen);
            if (str1.equals(divisor.repeat(str1.length() / divisorLen)) && str2.equals(divisor.repeat(str2.length() / divisorLen)) ) {
                return divisor;
            }
        }
        return "";
    }

}
