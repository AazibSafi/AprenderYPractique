package com.algorithms.aprenderypractique.algorithm.arrays.SubSetSum_OR_Partition_Array;

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
 *  Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
 *  to given total.
 *
 *  Another variation is given an array is it possible to split it up into 2 equal
 *  sum partitions. Partition need not be equal sized. Just equal sum.
 *
 *  https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 */
public class SubSetSum_DP_2D_Array extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{2, 3, 7, 8, 10};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(3,8),subsetSum(arr,11)));

        arr = new int[]{1, 2, 3};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(2,3),subsetSum(arr,5)));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(4,5),subsetSum(arr,9)));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(CommonHelper.isEquals(new ArrayList<>(),subsetSum(arr,30)));

        arr = new int[]{1, 8, 2, 5};
        Assert.assertTrue(CommonHelper.isEquals(new ArrayList<>(),subsetSum(arr,4)));

        arr = new int[]{2, 8, 2, 5};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(2,2),subsetSum(arr,4)));

        arr = new int[]{12, 7, 6, 7, 6};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(12,7),subsetSum(arr,19)));
    }

/*
 *      Time complexity - O(array_size * total_sum)     -- O(n*sum)
 *      Space complexity - O(array_size * total_sum)    -- bcz of 2-D Array
 */
    public List<Integer> subsetSum(int[] arr, int total) {
        boolean[][] dp = isSubSetSumPossible(arr,total);

        if(dp[dp.length - 1][dp[0].length - 1]) {
            return findElements(dp,arr);
        }

        return new ArrayList<>();

//      if only need to check the possibility of subset
//      return dp[dp.length-1][dp[0].length-1];
    }

    private boolean[][] isSubSetSumPossible(int[] arr, int total) {
        boolean[][] dp = new boolean[arr.length+1][total+1];

        for(int i=0;i<dp.length;i++) {
            dp[i][0] = true;
        }

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(j >= arr[i-1]) {         // i-1 is bcz we took extra 0th row in dp which is not available in arr
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp;
    }

    private List<Integer> findElements(boolean[][] dp, int[] arr) {
        List<Integer> list = new ArrayList<>();
        int i=dp.length-1, j=dp[0].length-1;

        while(i>0 && j>0) {
            if(dp[i][j] != dp[i-1][j]) {
                j = j-arr[i-1];
                list.add(arr[i-1]);
            }
            i--;
        }

        return list;
    }

}
