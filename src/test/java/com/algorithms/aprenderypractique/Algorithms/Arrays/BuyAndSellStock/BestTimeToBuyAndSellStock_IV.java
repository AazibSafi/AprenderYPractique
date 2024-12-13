package com.algorithms.aprenderypractique.Algorithms.Arrays.BuyAndSellStock;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class BestTimeToBuyAndSellStock_IV extends BaseTest {

    @Ignore
    @Test
    public void solution() {
        Assert.assertEquals(2, maxProfit(2, new int[]{2,4,1}));
        Assert.assertEquals(7, maxProfit(2, new int[]{3,2,6,5,0,3}));
    }

    public int maxProfit(int k, int[] prices) {
        return 0;
    }

}
