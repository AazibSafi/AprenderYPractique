package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Kadane's Algorithm
 *  Maximum Sum Subarray
 *  https://youtu.be/jnoVtCKECmQ?t=361
 *  https://www.interviewbit.com/problems/max-sum-contiguous-subarray/
 */
public class MaximumSumSubarray_KadaneAlgo extends BaseTest {

    @Test
    public void test() {
//      Testing sum of subArray
        int[] array = new int[] {-2,2,5,-11,6};
        Assert.assertEquals(7,maximumSumOfSubarray(array));

//      Testing subArray
        array = new int[] {-2,2,5,-11,6};
        Assert.assertArrayEquals(new int[]{1, 2}, maximumSumSubarray(array));
    }

/*
    Return the Sum of maximum subarray
        O(N)
 */
    public int maximumSumOfSubarray(int[] arr) {
        int currSum = arr[0];
        int maxSum = arr[0];

        for(int i=1;i<arr.length;i++) {
            currSum = Math.max(currSum + arr[i], arr[i]);
            maxSum = Math.max(currSum,maxSum);
        }

        return maxSum;
    }

/*
    Return the subarray that has maximum sum
    Extension of above method
        O(N)
 */
    public int[] maximumSumSubarray(int[] arr) {
        int i=0;
        int maxStart = 0, maxEnd = 0;
        int currSum = arr[0];
        int maxSum = arr[0];


        for(int j=1;j<arr.length;j++) {
            if(arr[j] > currSum + arr[j]) {
                currSum = arr[j];
                i = j;
            }
            else {
                currSum = currSum + arr[j];
            }

            if(currSum > maxSum) {
                maxSum = currSum;
                maxStart = i;
                maxEnd = j;
            }
        }

        return new int[]{maxStart,maxEnd};
    }

}
