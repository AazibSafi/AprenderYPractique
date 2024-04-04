package com.algorithms.aprenderypractique.algorithm.arrays.MonotonicStack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 *
 * https://www.youtube.com/watch?v=HRQB7-D2bi0
 **/
public class SumOfSubarrayMinimums extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{3,1,2,4};
        Assert.assertEquals(17, sumSubarrayMins(arr));

        arr = new int[]{11,81,94,43,3};
        Assert.assertEquals(444, sumSubarrayMins(arr));
    }

    /*
        Time: O(n)
        Space: O(n)
     */
    public int sumSubarrayMins(int[] arr) {
        long result = 0;
        long modulo = (long) 1e9+7;

        int[] NSL = getNextSmallerToLeft(arr);
        int[] NSR = getNextSmallerToRight(arr);

        long sum;
        for(int i=0; i<arr.length; i++) {
            long ls = i - NSL[i];
            long rs = NSR[i] - i;
            sum = ls * rs * arr[i];  // Total Sum of subarrays minimums containing the element arr[i]

            result = (result+sum)%modulo;
        }

        return (int) result;
    }

    public int[] getNextSmallerToLeft(int[] arr) {
        int[] nsl = new int[arr.length];
        Arrays.fill(nsl, -1);

        Stack<Integer> st = new Stack<>();  // Monotonic Stack

        for(int i=0; i<arr.length; i++) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i])
                st.pop();

            if(!st.isEmpty())
                nsl[i] = st.peek();

            st.push(i);
        }

        return nsl;
    }

    public int[] getNextSmallerToRight(int[] arr) {
        int n = arr.length;
        int[] nsr = new int[n];
        Arrays.fill(nsr, n);

        Stack<Integer> st = new Stack<>();  // Monotonic Stack

        for(int i=n-1; i>=0; i--) {

            while(!st.isEmpty() && arr[st.peek()] > arr[i])
                st.pop();

            if(!st.isEmpty())
                nsr[i] = st.peek();

            st.push(i);
        }

        return nsr;
    }

}
