package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.arrays.ArraySum.Two_Three_Sum.MaximumSumSubarray_KadaneAlgo;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.interviewbit.com/problems/flip/
 *  https://www.youtube.com/watch?v=K1cv2akL2w8
 *  https://www.youtube.com/watch?v=cLVpE5q_-DE
 */
public class Flip extends BaseTest {

    MaximumSumSubarray_KadaneAlgo maximumSumSubarray_kadaneAlgo = new MaximumSumSubarray_KadaneAlgo();

    @Test
    public void test() {
        String str = "010";
        Assert.assertArrayEquals(new int[]{0,0},flip(str));

        str = "011001001";
        Assert.assertArrayEquals(new int[]{3,7},flip(str));

        str = "110010011";
        Assert.assertArrayEquals(new int[]{2,6},flip(str));

        str = "111";        // Edge Case -> No operation can give us more than three 1s in final string. So, we return empty array [].
        Assert.assertArrayEquals(new int[]{},flip(str));
    }

/*
    Converting the problem into Maximum Sub array sum
    Return the indices of subarray
    Time: O(N)
    Space: O(N)
 */
    public int[] flip(String str) {

        if(str.indexOf('0') == -1) {     //   if there are no 0s, No operation can be performed
            return new int[]{};
        }

        int[] A = new int[str.length()];
        for(int i=0; i<A.length; i++) {
           A[i] = str.charAt(i) == '0' ? 1 : -1 ;
        }

        return maximumSumSubarray_kadaneAlgo.maxSumSubarray(A);
    }

}
