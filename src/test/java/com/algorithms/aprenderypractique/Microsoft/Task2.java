package com.algorithms.aprenderypractique.Microsoft;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/subarray-sum-equals-k/
 *  https://www.youtube.com/watch?v=AmlVSNBHzJg&t=3s
 */
public class Task2 extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(19, solution(14));
        Assert.assertEquals(11, solution(10));
        Assert.assertEquals(9999, solution(99));
    }

    public int solution(int N) {
        int sum = sumOfN(N);
        int twiceBig = sum*2;

        return findTheSmallestIntWhoseSumIs(twiceBig);
    }

    int findTheSmallestIntWhoseSumIs(int sum) {
        return (int) ((sum % 9 + 1) * Math.pow(10, (sum/9)) -1);
    }

    int sumOfN(int N) {
        int sum = 0;
        int rem;
        while(N != 0) {
            rem = N % 10;
            N = N / 10;
            sum += rem;
        }
        return sum;
    }

}
