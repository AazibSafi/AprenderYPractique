package com.algorithms.aprenderypractique.algorithm.arrays.ArraySum.Two_Three_Sum;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeSet;

/**
 *  https://www.interviewbit.com/problems/maximum-sum-triplet/
 *  https://www.youtube.com/watch?v=zyddBeqVvGs
 *  https://www.youtube.com/watch?v=l_hPdol4CSU
 */
public class MaxTripletSum extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{2,5,3,1,4,9};
        int sum = findMaxTripletSum(arr);
        Assert.assertEquals(16,sum);    //  {2,5,9} {3,4,9}

        arr = new int[]{1,2,3};
        sum = findMaxTripletSum(arr);
        Assert.assertEquals(6,sum);     //  {1,2,3}

        arr = new int[]{2,9,6,3,8,10};
        sum = findMaxTripletSum(arr);
        Assert.assertEquals(24,sum);        // {6,8,10}

        arr = new int[]{2,5,3,6,1,4,1};
        sum = findMaxTripletSum(arr);
        Assert.assertEquals(13,sum);        // {2,5,6}
    }

/*
    Time: O(nLogN)
    Space: O(N)     -- For using another array and set
 */
    public int findMaxTripletSum(int[] A) {
        int[] rightMax = fillRightMax(A);   // O(n)

        int max = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(A[0]);

        for(int i=1; i<A.length; i++) {                 // O(N)
            Integer left = set.lower(A[i]);             // O(LogN)
            if(left != null && rightMax[i] != -1) {
                max = Math.max(max, left + A[i] + rightMax[i]);
            }
            set.add(A[i]);
        }

        if(max > Integer.MIN_VALUE)
            return max;

        return 0;
    }

//  O(N)
    int[] fillRightMax(int[] A) {
        int n = A.length;
        int[] maxArr = new int[n];
        maxArr[n-1] = -1;

        for(n = n-2; n>=0; n--) {
            int rightMax = Math.max(A[n+1], maxArr[n+1]);
            maxArr[n] = rightMax > A[n] ? rightMax : -1;
        }
        return maxArr;
    }

}
