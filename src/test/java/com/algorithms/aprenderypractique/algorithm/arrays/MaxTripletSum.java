package com.algorithms.aprenderypractique.algorithm.arrays;

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
        Assert.assertEquals(16,sum);

        arr = new int[]{1,2,3};
        sum = findMaxTripletSum(arr);
        Assert.assertEquals(6,sum);
    }

/*
    Time: O(nLogN)
    Space: O(N)     -- For using another array and s set
 */
    public int findMaxTripletSum(int[] A) {
        int[] B = fillRightMax(A);   // O(n)

        int sum = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet();
        set.add(A[0]);

        Integer right, left;
        for(int j=1;j<A.length-1;j++) {     // O(n)
            right = B[j+1];
            left = set.lower(A[j]);     // O(nlogN)

            if(right > A[j] && left != null) {
                sum = Math.max(sum, left + A[j] + right);
            }
            set.add(A[j]);
        }

        if(sum > Integer.MIN_VALUE)
            return sum;

        return 0;
    }

//  O(n)
    public int[] fillRightMax(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        B[n-1] = A[n-1];

        for(int i=n-2;i>=0;i--) {
            B[i] = Math.max(A[i],B[i+1]);
        }
        return B;
    }

}
