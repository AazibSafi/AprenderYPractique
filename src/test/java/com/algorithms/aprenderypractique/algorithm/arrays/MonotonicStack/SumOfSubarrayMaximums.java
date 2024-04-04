package com.algorithms.aprenderypractique.algorithm.arrays.MonotonicStack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Similar to
 * @see SumOfSubarrayMinimums
 *
 * just change the condition of arr[st.peek()] <= arr[i]
 **/
public class SumOfSubarrayMaximums extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{3,1,2,4};
        Assert.assertEquals(30, sumSubarrayMaxs(arr));

        arr = new int[]{11,81,94,43,3};
        Assert.assertEquals(1108, sumSubarrayMaxs(arr));
    }

    /*
        Time: O(n)
        Space: O(n)
     */
    public int sumSubarrayMaxs(int[] arr) {
        long result = 0;
        long modulo = (long) 1e9+7;

        int[] NGL = getNextGreaterToLeft(arr);
        int[] NGR = getNextGreaterToRight(arr);

        long sum;
        for(int i=0; i<arr.length; i++) {
            long ls = i - NGL[i];
            long rs = NGR[i] - i;
            sum = ls * rs * arr[i];  // Total Sum of subarrays maximums containing the element arr[i]

            result = (result+sum)%modulo;
        }

        return (int) result;
    }

    public int[] getNextGreaterToLeft(int[] arr) {
        int[] nsl = new int[arr.length];
        Arrays.fill(nsl, -1);

        Stack<Integer> st = new Stack<>();  // Monotonic Stack

        for(int i=0; i<arr.length; i++) {
            while(!st.isEmpty() && arr[st.peek()] <= arr[i])
                st.pop();

            if(!st.isEmpty())
                nsl[i] = st.peek();

            st.push(i);
        }

        return nsl;
    }

    public int[] getNextGreaterToRight(int[] arr) {
        int n = arr.length;
        int[] nsr = new int[n];
        Arrays.fill(nsr, n);

        Stack<Integer> st = new Stack<>();  // Monotonic Stack

        for(int i=n-1; i>=0; i--) {
            while(!st.isEmpty() && arr[st.peek()] < arr[i])
                st.pop();

            if(!st.isEmpty())
                nsr[i] = st.peek();

            st.push(i);
        }

        return nsr;
    }

}
