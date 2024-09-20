package com.algorithms.aprenderypractique.algorithm.arrays.Digits.SmallestNumberOfSumOfDigits;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.geeksforgeeks.org/find-the-smallest-number-whose-sum-of-digits-is-n/
 *  Find the smallest number whose sum of digits is N
 */
public class SmallestNumberWhoseSumOfDigits extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(19, smallestNumber(10));
        Assert.assertEquals(99, smallestNumber(18));
        Assert.assertEquals(599, smallestNumber(23));
        Assert.assertEquals(9, smallestNumber(9));
        Assert.assertEquals(0, smallestNumber(0));
    }

/*
    Put 9 from end until the sum is less than 9
    Put the remaining sum at first place

    Time: O(N)
 */
    int smallestNumber(int N) {
        StringBuilder s = new StringBuilder();

        while(N > 9) {
            s.insert(0, '9');
            N -= 9;
        }

        s.insert(0, N);
        return Integer.parseInt(s.toString());
    }

//  Using Math Formula
    int smallestNumber2(int N) {
        return (int) ( (N % 9 + 1) * Math.pow(10, (N / 9)) - 1 );
    }

}
