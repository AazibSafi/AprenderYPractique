package com.algorithms.aprenderypractique.algorithm.arrays.BuyAndSellStock;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *  https://www.youtube.com/watch?v=K8iHi8AW1ls
 */
public class BestTimeToBuyAndSellStock_II extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(7, maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(4, maxProfit(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }

/*  Time: O(n)  --  Only a single pass is needed
    Valley Peak Approach
 */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }

}
