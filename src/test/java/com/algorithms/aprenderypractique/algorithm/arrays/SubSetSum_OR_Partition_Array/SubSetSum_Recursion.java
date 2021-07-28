package com.algorithms.aprenderypractique.algorithm.arrays.SubSetSum_OR_Partition_Array;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://www.youtube.com/watch?v=34l1kTIQCIA
 *  https://www.youtube.com/watch?v=s6FhG--P7z0
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubsetSum.java
 *
 *  Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
 *  to given total.
 *
 *  https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 */
public class SubSetSum_Recursion extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{2, 3, 7, 8, 10};
        Assert.assertTrue(isSubsetSumPossible(arr,arr.length,11));

        arr = new int[]{2, 2, 3};
        Assert.assertTrue(isSubsetSumPossible(arr,arr.length,5));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(isSubsetSumPossible(arr,arr.length,9));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertFalse(isSubsetSumPossible(arr,arr.length,30));

        arr = new int[]{1, 8, 2, 5};
        Assert.assertFalse(isSubsetSumPossible(arr,arr.length,4));

        arr = new int[]{12, 7, 6, 7, 6};
        Assert.assertTrue(isSubsetSumPossible(arr,arr.length,14));
    }

/*
    Time:   -- Exponential
    Non-Optimal Solution
 */
    public boolean isSubsetSumPossible(int[] arr, int n, int total) {
        if(total==0)    return  true;
        if(n==0)   return false;

// If last element is greater than sum then exclude it.
        if(arr[n-1] > total)
            isSubsetSumPossible(arr,n-1,total);

//      check if sum can be obtained by any of the following:
//      (a) including the last element
//      (b) excluding the last element
        return isSubsetSumPossible(arr,n-1,total-arr[n-1]) || isSubsetSumPossible(arr,n-1,total);
    }


    //@Ignore
    @Test
    public void test2() {
        int[] arr = new int[]{2, 3, 7, 8, 10};
        Assert.assertTrue(subsetSum(arr,11));

        arr = new int[]{2, 2, 3};
        Assert.assertTrue(subsetSum(arr,5));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(subsetSum(arr,9));

        arr = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertFalse(subsetSum(arr,30));

        arr = new int[]{12, 7, 6, 7, 6};
        Assert.assertTrue(subsetSum(arr,14));
    }

/*
    Currently not working properly
 */
    public boolean subsetSum(int[] arr, int total) {
        int[][] mem = new int[arr.length][total+1];

        for(int[] row : mem) {
            Arrays.fill(row,-1);
        }

        return isPossible_Memoization(arr,0,total,mem) == 1;
    }

/*
    Time Complexity: O(sum*n), where sum is the ‘target sum’ and ‘n’ is the size of array.
    Auxiliary Space: O(sum*n), as the size of 2-D array is sum*n.
    Memoization Optimal Solution
 */
    public int isPossible_Memoization(int[] arr, int i, int total, int[][] mem) {
        if(total==0)    return  1;  // true
        if(i>=arr.length || total<0)   return 0;        // false

// If the value is not -1 it means it
// already call the function
// with the same value.
// it will save our from the repetition.
        if(mem[i][total] != -1)       return mem[i][total];

        if(arr[i] > total)        isPossible_Memoization(arr, i+1, total, mem);

        return mem[i][total] = OR( isPossible_Memoization(arr,i+1,total-arr[i],mem),
                                    isPossible_Memoization(arr,i+1,total,mem) );
    }

    private int OR(int a, int b) {
        return ( a==1 ? 1 : (b==1 ? 1 : 0) );
    }

}
