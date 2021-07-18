package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://www.interviewbit.com/problems/max-non-negative-subarray/
 */
public class MaxNonNegativeSubArray extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 5, -7, 2, 3};
        Assert.assertArrayEquals(new int[]{1,2,5},maxSet(arr));

        arr = new int[]{10, -1, 2, 3, -4, 100};
        Assert.assertArrayEquals(new int[]{100},maxSet(arr));
    }

/*
    O(N)
 */
    public int[] maxSet(int[] A) {
        int startMax=0, endMax=0, sum=0, maxSum = 0;

        for(int i=0;i<A.length;i++) {
            int j=i;
            sum=0;

            while(j<A.length && A[j]>=0) {
                sum += A[j];
                j++;
            }

            if((sum > maxSum) || (sum == maxSum && j-i > endMax-startMax)) {
                maxSum = sum;
                startMax = i;
                endMax = j;
            }
            i=j;
        }

        return Arrays.copyOfRange(A,startMax,endMax);
    }

}
