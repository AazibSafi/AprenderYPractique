package com.algorithms.aprenderypractique.algorithm.arrays.ArraySum.SubSetSum_OR_Partition_Array;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://www.youtube.com/watch?v=34l1kTIQCIA
 *  https://www.youtube.com/watch?v=s6FhG--P7z0
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubsetSum.java
 *
 *  Given an array of non-negative numbers and a total, is there subset of numbers in this array which adds up
 *  to given total.
 *
 *  Another variation is given an array is it possible to split it up into 2 equal
 *  sum partitions. Partition need not be equal sized. Just equal sum.
 *
 *  https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 */
public class SubSetSum_DP_1D_Array extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{2, 3, 7, 8, 10};
        Assert.assertTrue(isSubSetSumPossible(arr,11));

        arr = new int[]{1, 2, 3};
        Assert.assertTrue(isSubSetSumPossible(arr,5));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(isSubSetSumPossible(arr,9));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertFalse(isSubSetSumPossible(arr,30));

        arr = new int[]{1, 8, 2, 5};
        Assert.assertFalse(isSubSetSumPossible(arr,4));

        arr = new int[]{12, 7, 6, 7, 6};
        Assert.assertTrue(isSubSetSumPossible(arr,19));
    }

/*
 *      Time complexity - O(array_size * total_sum)     -- O(n*sum)
 *      Space complexity - O(total_sum)    -- bcz of 1-D Array
 */
    public boolean isSubSetSumPossible(int[] arr, int total) {
        boolean[] dp = new boolean[total+1];
        dp[0] = true;       // if sum is 0 then the possibility is always true because empty { } elements are always a subset of Array

        for(int i=0; i<arr.length; i++) {
            for(int j=dp.length-1; j>=0; j--) {
                if(j >= arr[i]) {
                    dp[j] = dp[j] || dp[j-arr[i]];
                }
            }
        }

        return dp[dp.length - 1];
    }

    @Test
    public void testSubSet() {
        int[] arr = new int[]{2, 3, 7, 8, 10};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(3,8),findPossibleSubsetSum(arr,11)));

        arr = new int[]{1, 2, 3};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(2,3),findPossibleSubsetSum(arr,5)));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(4,5),findPossibleSubsetSum(arr,9)));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(CommonHelper.isEquals(new ArrayList<>(),findPossibleSubsetSum(arr,30)));

        arr = new int[]{1, 8, 2, 5};
        Assert.assertTrue(CommonHelper.isEquals(new ArrayList<>(),findPossibleSubsetSum(arr,4)));

        arr = new int[]{12, 7, 6, 7, 6};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(12,7),findPossibleSubsetSum(arr,19)));
    }

/*
 *      Time complexity - O(array_size * total_sum)     -- O(n*sum)
 *      Space complexity - O(array_size * total_sum)    -- bcz 2 Arrays are used
 *
 *      Formula:    T[i][j] || T[i][j-arr[i]]    -->   T || F
 */
    private List<Integer> findPossibleSubsetSum(int[] arr, int total) {
        boolean[] dp = new boolean[total+1];
        dp[0] = true;       // if sum is 0 then the possibility is always true because empty { } elements are always a subset of Array

        int[] selectedElements = new int[total+1];

        for(int i=0; i<arr.length; i++) {
            for(int j=dp.length-1; j>=0; j--) {
                if(j >= arr[i]) {
                    if(!dp[j] && dp[j-arr[i]]) {        // if False && True
                        dp[j] = dp[j-arr[i]];
                        selectedElements[j] = arr[i];
                    }
                }
            }
        }

        if(dp[dp.length - 1]) {
            return findElements(selectedElements);
        }

        return new ArrayList<>();
    }

    private List<Integer> findElements(int[] selectedElements) {
        List<Integer> result = new ArrayList<>();
        int n = selectedElements.length-1;

        while( n > 0 ) {
            result.add(selectedElements[n]);
            n = n - selectedElements[n];
        }
        return result;
    }

}
