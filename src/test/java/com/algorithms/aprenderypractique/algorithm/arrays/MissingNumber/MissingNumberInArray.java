package com.algorithms.aprenderypractique.algorithm.arrays.MissingNumber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://www.geeksforgeeks.org/find-the-missing-number/
 *  https://www.youtube.com/watch?v=lBk6fVhuCyw
 *
 *  You are given a list of n-1 integers and these integers are in the range of 1 to n.
 *  There are no duplicates in the list. One of the integers is missing in the list.
 */
public class MissingNumberInArray extends BaseTest {

    @Test
    public void solution() {
        int[] nums = new int[]{1, 2, 4, 6, 3, 7, 8};
        Assert.assertEquals(5,findMissingNumberInContigousArray_XOR(nums));

        nums = new int[]{1, 2, 3, 5};
        Assert.assertEquals(4,findMissingNumberInContigousArray_XOR(nums));
    }

//  Efficient Solution
    int findMissingNumberInContigousArray_XOR(int[] nums) {
        int a = 1;
        int b = nums[0];

        for(int i=2; i<=nums.length+1; i++) {
            a = a ^ i;
        }

        for(int i=1; i<nums.length; i++) {
            b = b ^ nums[i];
        }

        return a ^ b;
    }

/*
    Time: O(n)
    PROBLEM:
        If the sum of the numbers goes beyond maximum allowed integer,
        then there can be integer overflow and we may not get correct answer.
 */
    int findMissingNumberInContigousArray_SumFormula(int[] nums) {
        int numsSum = Arrays.stream(nums).sum();

        int n = nums.length + 1;    // PLUS 1 -->  bcz given array has n-1 numbers, but the actual array contains n+1 numbers
        int totalSum =  n*(n+1) / 2;

        return totalSum - numsSum;
    }

}
