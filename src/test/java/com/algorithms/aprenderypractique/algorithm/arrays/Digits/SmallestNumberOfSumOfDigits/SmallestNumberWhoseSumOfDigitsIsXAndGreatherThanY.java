package com.algorithms.aprenderypractique.algorithm.arrays.Digits.SmallestNumberOfSumOfDigits;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  https://www.geeksforgeeks.org/smallest-number-greater-than-y-with-sum-of-digits-equal-to-x/?ref=rp
 *  Function to find the smallest number greater than Y whose sum of digits is X
 *
 *  Logic Not Understood, Just copied from Internet
 */
public class SmallestNumberWhoseSumOfDigitsIsXAndGreatherThanY extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("189", findMin(18,99));
        Assert.assertEquals("75", findMin(12,72));
    }

/*
    Function to find the smallest
    number greater than Y
    whose sum of digits is X

    Time Complexity: O(log10Y)
    Auxiliary Space: O(log10Y)
 */
    String findMin(int X, int Y) {

        // Convert number y to String
        String y = Integer.toString(Y);

        int n = y.length();

        List<Integer> prefix = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            prefix.add(0);
        }

        // Maintain prefix sum of digits
        for(int i = 0; i < n; i++) {
            prefix.add(i, y.charAt(i) - '0');

            if (i > 0) {
                prefix.add(i, prefix.get(i) + prefix.get(i - 1));
            }
        }

        // Iterate over Y from the back where k is current length of suffix
        for(int i = n - 1, k = 0;; i--, k++) {

            // Stores current digit
            int d = 0;

            if (i >= 0) {
                d = y.charAt(i) - '0';
            }

            // Increase current digit
            for(int j = d + 1; j <= 9; j++) {
                int r = j;

                // Sum upto current prefix
                if (i > 0) {
                    r += prefix.get(i - 1);
                }

                // Return answer if remaining
                // sum can be obtained in suffix
                if (X - r >= 0 && X - r <= 9 * k) {

                    // Find suffix of length k
                    // having sum of digits X-r
                    String suf = helper(k, X - r);

                    String pre = "";

                    if (i > 0)
                        pre = y.substring(0, i);

                    // Append current character
                    char cur = (char) (j + '0');
                    pre += cur;

                    // Return the result
                    return pre + suf;
                }
            }
        }
    }

    String helper(int d, int s) {
        // Return a String of length d
        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < d; i++) {
            ans.append("0");
        }

        for(int i = d - 1; i >= 0; i--) {

            // Greedily put 9's in the end
            if (s >= 9) {
                ans.setCharAt(i,'9');
                s -= 9;
            }

            // Put remaining sum
            else {
                char c = (char) (s + '0');
                ans.setCharAt(i,  c);
                s = 0;
            }
        }
        return ans.toString();
    }

}
