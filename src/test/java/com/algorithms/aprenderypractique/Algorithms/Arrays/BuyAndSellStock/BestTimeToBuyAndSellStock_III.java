package com.algorithms.aprenderypractique.Algorithms.Arrays.BuyAndSellStock;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *  https://www.youtube.com/watch?v=YAWRyWJalM0
 */
public class BestTimeToBuyAndSellStock_III extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(6, maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        Assert.assertEquals(4, maxProfit(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
        Assert.assertEquals(0, maxProfit(new int[]{1}));
    }

/*
    Time: O(n)    --  Only a single pass is needed.
    Space: O(1)   --  Only two variables are used.
 */
    public int maxProfit(int[] prices) {
        int minPrice1 = Integer.MAX_VALUE;
        int maxProfit1 = 0;
        int minPrice2 = Integer.MAX_VALUE;
        int maxProfit2 = 0;

        for (int price : prices) {
            minPrice1 = Math.min(price, minPrice1);
            maxProfit1 = Math.max(price - minPrice1, maxProfit1);

            minPrice2 = Math.min(price - maxProfit1, minPrice2);
            maxProfit2 = Math.max(price - minPrice2, maxProfit2);
        }

        return maxProfit2;
    }

}
