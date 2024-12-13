package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum.Two_Three_Sum;

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
        Assert.assertArrayEquals(new int[]{1,2,5}, maxSet(arr));

        arr = new int[]{10, -1, 2, 3, -4, 100};
        Assert.assertArrayEquals(new int[]{100}, maxSet(arr));

        arr = new int[]{-1, -1, -1, -1, -1};
        Assert.assertArrayEquals(new int[]{}, maxSet(arr));

        arr = new int[]{0, 0, -1, 0};
        Assert.assertArrayEquals(new int[]{0,0}, maxSet(arr));

        arr = new int[]{1967513926, 1540383426, -1303455736, -521595368};
        Assert.assertArrayEquals(new int[]{1967513926, 1540383426}, maxSet(arr));
    }

    //  O(N)
    public int[] maxSet(int[] A) {
        int iMax = 0, jMax = 0;
        long maxSum = 0, currSum=0;     // Edge Case: Use Long to store the sum larger than the integer limit
        int n = A.length;

        int i=0;
        for(int j=0; j<n; j++) {
            if(A[j] < 0) {
                currSum = 0;
                i = j+1;
            }
            else {
                currSum += A[j];
                if((currSum > maxSum) || (currSum == maxSum && j-i+1 > jMax-iMax)) { // Edge case, for priority check condition
                    maxSum = currSum;
                    iMax = i;
                    jMax = j+1;
                }
            }
        }

        return Arrays.copyOfRange(A, iMax, jMax);
    }

}
