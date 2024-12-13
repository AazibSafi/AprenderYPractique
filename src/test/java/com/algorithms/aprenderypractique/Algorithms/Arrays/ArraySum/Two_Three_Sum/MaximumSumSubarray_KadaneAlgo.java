package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum.Two_Three_Sum;

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
        int[] array = new int[] {1, 2, 3, 4, -10};
        Assert.assertEquals(10, maxSubArray(array));

        array = new int[] {-2, 2, 5, -11, 6};
        Assert.assertEquals(7, maxSubArray(array));

        array = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertEquals(6, maxSubArray(array));

        array = new int[] {-1};
        Assert.assertEquals(-1, maxSubArray(array));

//      Testing subArray
        array = new int[] {-2, 2, 5, -11, 6};
        Assert.assertArrayEquals(new int[]{1, 2}, maxSumSubarray(array));       //  { maxStart , maxEnd }

        array = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertArrayEquals(new int[]{3, 6}, maxSumSubarray(array));
    }

/*
    Return the Sum of maximum subarray
    Time Complexity: O(N)
 */
    public int maxSubArray(int[] A) {
        int currSum = A[0], maxSum = A[0];

        for(int i=1; i<A.length; i++) {
            currSum = Math.max(A[i], currSum+A[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

/*
    Return the subarray that has maximum sum
    Extension of above method
    Time Complexity: O(N)
 */
    public int[] maxSumSubarray(int[] arr) {
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

        return new int[]{maxStart, maxEnd};
    }

}
