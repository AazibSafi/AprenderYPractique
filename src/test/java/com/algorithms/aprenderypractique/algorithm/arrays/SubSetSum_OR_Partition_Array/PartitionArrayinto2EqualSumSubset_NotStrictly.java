package com.algorithms.aprenderypractique.algorithm.arrays.SubSetSum_OR_Partition_Array;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/partition-equal-subset-sum/
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=226994905008716
 *
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubsetSum.java
 *
 * https://www.youtube.com/watch?v=obhWqDfzwQQ
 *
 * Given an array is it possible to split it up into 2 equal sum partitions.
 * Partition need not be equal sized. Just equal sum
 *
 *  NOTE: All of the integers in A array are NOT strictly smaller than all of the integers in B array
 *
 *  Extension of  SubSetSum Problem
 */
public class PartitionArrayinto2EqualSumSubset_NotStrictly extends BaseTest {

    @Test
    public void test() {
    int[] arr = new int[]{1,5,11,5};    //  { 1,5,5 } { 11 }
        Assert.assertTrue(canPartition(arr));

        arr = new int[]{2,3,7,8,10};    //  { 2,3,10 } { 7,8 }
        Assert.assertTrue(canPartition(arr));

        arr = new int[]{1,2,3,5};
        Assert.assertFalse(canPartition(arr));

        arr = new int[]{1,6,3};
        Assert.assertFalse(canPartition(arr));
    }

/*
        @see SubSetSum_DP_1D_Array Algorithm
 *      Time complexity - O(array_size * partitionalSum)     -- O(n*sum)
 *      Space complexity - O(total_sum)    -- bcz of 1-D Array

     Here we can use SubSet Sum Problem to check if it is possible to get the partitionalSum in the array
     if any subset has the partitionalSum that means the rest of the array must have other partitionalSum which makes it totalSum
 */
    public boolean canPartition(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        if(totalSum%2 == 1)     return false;   // if the total sum is odd, partition cannot be possible

        int partitionalSum = totalSum/2;

        SubSetSum_Recursion algorithm = new SubSetSum_Recursion();
        return algorithm.subsetSum(arr, partitionalSum);
    }

}
