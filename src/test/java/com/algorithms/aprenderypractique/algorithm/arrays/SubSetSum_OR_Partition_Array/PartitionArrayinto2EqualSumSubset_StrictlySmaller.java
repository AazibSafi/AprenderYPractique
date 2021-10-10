package com.algorithms.aprenderypractique.algorithm.arrays.SubSetSum_OR_Partition_Array;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/discuss/interview-question/718692/facebook-training-balanced-split
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=226994905008716
 *
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubsetSum.java
 *
 * https://www.youtube.com/watch?v=obhWqDfzwQQ
 *
 * Given an array is it possible to split it up into 2 equal sum partitions.
 * Partition need not be equal sized. Just equal sum
 *
 *  NOTE: All of the integers in A array are STRICTLY smaller than all of the integers in B array
 *
 *  Extension of  SubSetSum Problem
 */
public class PartitionArrayinto2EqualSumSubset_StrictlySmaller extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{2,3,5};   // {2 3} {5}
        Assert.assertTrue(canPartition(arr));

        arr = new int[]{1,2,3,6};       //  {1 2 3} {6}
        Assert.assertTrue(canPartition(arr));

        arr = new int[]{2, 1, 2, 5};    // {2 1 2}{5}
        Assert.assertTrue(canPartition(arr));

        arr = new int[]{1, 5, 1, 7};    // {1 5 1} {7}
        Assert.assertTrue(canPartition(arr));

        arr = new int[]{1,5,11,5};
        Assert.assertFalse(canPartition(arr));

        arr = new int[]{2,3,7,8,10};
        Assert.assertFalse(canPartition(arr));

        arr = new int[]{12, 7, 6, 7, 6};
        Assert.assertFalse(canPartition(arr));

        arr = new int[]{1,2,6,3};
        Assert.assertFalse(canPartition(arr));

        arr = new int[]{3, 6, 3, 4, 4};
        Assert.assertFalse(canPartition(arr));

        arr = new int[]{1, 5, 7, 1};
        Assert.assertFalse(canPartition(arr));

        arr = new int[]{12, 7, 6, 7, 6};
        Assert.assertFalse(canPartition(arr));
    }

/*
    Time: O(n)
 */
    boolean canPartition(int[] arr) {
        int len = arr.length;
        if (len == 0)   return false;

        //Arrays.sort(arr); -- No Needed    -- it was written in internet code but here all test cases are passed without sorting

        int leftSum = 0;
        int rightsum = arr[len - 1];
        for (int i = 0; i < len - 1; i++) {
            leftSum += arr[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            if (leftSum == rightsum && arr[i] < arr[i + 1]) return true;
            if (leftSum < rightsum) break;

            leftSum -= arr[i];
            rightsum += arr[i];
        }

        return false;
    }

}
