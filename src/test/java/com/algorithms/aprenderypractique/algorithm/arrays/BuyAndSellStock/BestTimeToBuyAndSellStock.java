package com.algorithms.aprenderypractique.algorithm.arrays.BuyAndSellStock;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *  https://www.youtube.com/watch?v=mj7N8pLCJ6w&ab_channel=KevinNaughtonJr.
 *  https://www.youtube.com/watch?v=1pkOgXD63yU&ab_channel=NeetCode
 */
public class BestTimeToBuyAndSellStock  extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(5, maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }

/*
    Time: O(n)    --  Only a single pass is needed.
    Space: O(1)   --  Only two variables are used.
 */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
//            if (price < minPrice)
//                minPrice = price;
//
//            else if ((price - minPrice) > maxProfit)
//                maxProfit = (price - minPrice);


            minPrice = Math.min(price, minPrice);
            maxProfit = Math.max(price - minPrice, maxProfit);
        }

        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        if(prices.length == 0)      return 0;

        int max = 0;

//      Left = Buy
//      Right = Sell
        int left = 0, right = 1;

        while(right < prices.length) {
            if(prices[left] > prices[right]) {
                left++;
            }
            else {
                max = Math.max(max, prices[right] - prices[left]);
                right++;
            }
        }

        return max;
    }

}
