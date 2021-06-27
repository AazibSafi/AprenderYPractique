package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *  Kadane's Algorithm
 *  Maximum Sum Subarray
 *  https://youtu.be/jnoVtCKECmQ?t=361
 */
public class MaximumSumSubarray_KadaneAlgo extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {-2,2,5,-11,6};
        System.out.println(maximumSumSubarray(array));
    }

//    O(N)
    public int maximumSumSubarray(int[] arr) {
        int currSum = arr[0];
        int maxSum = arr[0];

        for(int i=1;i<arr.length;i++) {
            currSum = Math.max(currSum + arr[i], arr[i]);
            maxSum = Math.max(currSum,maxSum);
        }

        return maxSum;
    }

}
