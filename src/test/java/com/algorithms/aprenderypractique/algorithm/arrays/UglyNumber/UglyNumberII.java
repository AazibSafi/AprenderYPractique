package com.algorithms.aprenderypractique.algorithm.arrays.UglyNumber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 *      https://leetcode.com/problems/ugly-number-ii
 */
public class UglyNumberII extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(12, nthUglyNumber(10));
        Assert.assertEquals(1, nthUglyNumber(1));
        System.out.printf(String.valueOf(Arrays.stream(new int[0]).filter(p -> p==2).count()));
    }

/*
    Time: O(nlogm)
    Space: O(m)
    Let n be the given index value of the ugly number and m be the size of set.
 */
    public int nthUglyNumber(int n) {
        TreeSet<Long> uglyNumbersSet = new TreeSet<>(); // TreeSet to store potential ugly numbers
        uglyNumbersSet.add(1L); // Start with 1, the first ugly number
        // TreeSet automatically sorts elements in ascending order and does not
        // allow duplicate entries, just like a HashSet in python

        Long currentUgly = 1L;
        for (int i = 0; i < n; i++) {
            currentUgly = uglyNumbersSet.pollFirst(); // Get the smallest number from the set and remove it

            // Insert the next potential ugly numbers into the set
            uglyNumbersSet.add(currentUgly * 2);
            uglyNumbersSet.add(currentUgly * 3);
            uglyNumbersSet.add(currentUgly * 5);
        }

        return currentUgly.intValue(); // Return the nth ugly number
    }

}
