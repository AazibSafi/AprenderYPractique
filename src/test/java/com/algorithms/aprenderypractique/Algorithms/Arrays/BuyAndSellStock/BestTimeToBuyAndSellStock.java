package com.algorithms.aprenderypractique.Algorithms.Arrays.BuyAndSellStock;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *  https://www.youtube.com/watch?v=mj7N8pLCJ6w&ab_channel=KevinNaughtonJr.
 *  https://www.youtube.com/watch?v=1pkOgXD63yU&ab_channel=NeetCode
 *
 *  Todo: https://www.educative.io/find-maximum-single-sell-profit
 */
public class BestTimeToBuyAndSellStock extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(5, maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
        Assert.assertEquals(14, maxProfit(new int[]{8,5,12,9,19,1}));
    }

/*
    Time: O(n)    --  Only a single pass is needed.
    Space: O(1)   --  Only two variables are used - No extra space
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

    boolean regxMatch(String text, String pattern) {
        Boolean[][] memo = new Boolean[text.length()][pattern.length()];
        return match(text, pattern, 0, 0, memo);
    }

    boolean match(String text, String pattern, int i, int j, Boolean[][] memo) {
        int m = text.length(), n = pattern.length();
        if(i>m || j>n) return false;
        if(j == n)  return i == m;

        if(memo[i][j] != null)  return memo[i][j];

        boolean matched = false;

        boolean firstMath = i<m && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');

        if(j+1<n && pattern.charAt(j+1) == '*') {
            matched = match(text, pattern, i, j+2, memo) || (firstMath && match(text, pattern, i+1, j, memo));
        }
        else
            matched = firstMath && match(text, pattern, i+1, j+1, memo);

        return memo[i][j] = matched;
    }

}
