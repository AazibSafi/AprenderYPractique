package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 *      https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=547645422524434
 *
 *      You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that, for each index i (between 0 and n-1, inclusive), output[i] is equal to the median of the elements arr[0..i] (rounded down to the nearest integer).
 *      The median of a list of integers is defined as follows. If the integers were to be sorted, then:
 *      If there are an odd number of integers, then the median is equal to the middle integer in the sorted order.
 *      Otherwise, if there are an even number of integers, then the median is equal to the average of the two middle-most integers in the sorted order.
 *
 *      TODO
 *      https://leetcode.com/problems/find-median-from-data-stream/
 *      https://leetcode.com/problems/ipo/
 */
public class MedianStream extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{5, 15, 1, 3};
        Assert.assertArrayEquals(new int[]{5, 10, 5, 4}, findMedian(arr));

        arr = new int[]{2, 4, 7, 1, 5, 3};
        Assert.assertArrayEquals(new int[]{2, 3, 4, 3, 4, 3}, findMedian(arr));

        arr = new int[]{1, 2};
        Assert.assertArrayEquals(new int[]{1, 1}, findMedian(arr));
    }

/*
    Time: O(nLogN)
 */
    public int[] findMedian(int[] arr) {
        int[] result = new int[arr.length];
        Set<Integer> set = new TreeSet<>();     // to make the list in sorted order

        for(int i=0; i<arr.length; i++) {        // O(n)
            set.add(arr[i]);        // O(logN)

            int len = set.size();
            Integer[] setArray = new Integer[len];
            setArray = set.toArray(setArray);

            int mid = len/2;

            if(len%2 == 1)    // Odd Length
                result[i] = setArray[mid];
            else              // Even Length
                result[i] = (setArray[mid] + setArray[mid-1])/2;
        }

        return result;
    }

}
