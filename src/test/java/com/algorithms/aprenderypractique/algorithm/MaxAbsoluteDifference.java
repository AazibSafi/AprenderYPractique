package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.interviewbit.com/problems/maximum-absolute-difference/
 *  https://www.youtube.com/watch?v=TPVctnIaK7Q
 *  https://www.youtube.com/watch?v=Ov4weYCIipg
 */
public class MaxAbsoluteDifference extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {1,3,-1};
        Assert.assertEquals(5, maxArr(array));

        array = new int[] {2,2,2};
        Assert.assertEquals(2, maxArr(array));
    }

/*
    O(N)
 */
    public int maxArr(int[] A) {
        int max1 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int i=0;i<A.length;i++) {
            max1 = Math.max(max1, A[i]+i);
            min1 = Math.min(min1, A[i]+i);

            max2 = Math.max(max2, A[i]-i);
            min2 = Math.min(min2, A[i]-i);
        }

        return Math.max(max1-min1,max2-min2);
    }
/*
    Brute Force O(n 2)
 */
    public int maxArr2(int[] A) {
        int max = 0;
        int curr;

        for(int i=1;i<=A.length;i++) {
            for(int j=i;j<=A.length;j++) {
                if(i==j) {
                    curr = 0;
                }
                else {
                    curr = func(A,i-1,j-1);
                }
                max = checkMax(curr,max);
            }
        }
        return max;
    }

    public int func(int[] A,int i, int j) {
        return Math.abs(A[i] - A[j]) + Math.abs(i - j);
    }

    public int checkMax(int curr, int max) {
        return curr > max ? curr : max;
    }

}
