package com.algorithms.aprenderypractique.Amazon;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/*
    https://leetcode.com/discuss/interview-question/1321204/efficient-harvest-faang-oa-question-2021
    https://algo.monster/problems/amazon_oa_max_profit
    Solution: https://cybergeeksquad.co/2022/02/max-profit-amazon-oa-solution.html
 */
public class MaxProfit_EfficientHarvest extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(16, maxProfit(2, new int[]{1, 5, 1, 3, 7, -3}));
        Assert.assertEquals(0, maxProfit(1, new int[]{-6,3,6,-3}));
        Assert.assertEquals(-2, maxProfit(1, new int[]{3,-5}));
    }

/*
    Time: O(N + K)
    Sliding Window
    Efficient Solution

    Algo: Subtracting the left item from window and adding the new item in the right of the window
 */
    public int maxProfit(int k, int[] profit) {
        int len = profit.length;

        int currProfit = 0;
        for(int i=0; i<k; i++) {
            currProfit += profit[i];
            currProfit += profit[oppIndex(i, len)];
        }

        int max = currProfit;
        for(int i=0; i<len/2 - 1 ;i++) {
            currProfit -= profit[i];
            currProfit -= profit[oppIndex(i, len)];

            currProfit += profit[i + k + 1];
            currProfit += profit[oppIndex(i + k + 1, len)];

            max = Math.max(max, currProfit);
        }

        return max;
    }

/*
    Time: O(N*K)
    Key is to find opponent Index
 */
    public int maxProfit2(int k, int[] profit) {
        int max = Integer.MIN_VALUE;
        int len = profit.length;

        for(int i=0; i<len/2; i++) {
            int sum = 0;

            for(int j=i; j<i+k; j++) {
                sum += profit[j];
                sum += profit[oppIndex(j, len)];
            }

            max = Math.max(max, sum);
        }

        return max;
    }

    int oppIndex(int curr, int n) {
        return (curr + n/2) % n;
    }

}
