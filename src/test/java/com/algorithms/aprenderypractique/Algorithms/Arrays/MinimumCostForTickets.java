package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/minimum-cost-for-tickets
 */
public class MinimumCostForTickets extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(11, mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
        Assert.assertEquals(17, mincostTickets(new int[]{1,2,3,4,5,6,7,8,9,10,30,31}, new int[]{2,7,15}));
    }

    /*
        Approach#2: BackTracking [with Memoization
        Time: O(n)
        Space: O(n)
    */
    public int mincostTickets(int[] days, int[] costs) {
        Map<String, Integer> memo = new HashMap<>();
        return backtrack(days, costs, 0, 0, memo);
    }

    int backtrack(int[] days, int[] costs, int i, int currPassValidity, Map<String, Integer> memo) {
        if(i >= days.length)
            return 0;

        String key = i + "#" + currPassValidity;
        if(memo.containsKey(key))
            return memo.get(key);

        if(days[i] <= currPassValidity)
            return backtrack(days, costs, i + 1, currPassValidity, memo); // Skip day if within pass validity

        int dayPass1 = costs[0] + backtrack(days, costs, i+1, days[i], memo);
        int dayPass7 = costs[1] + backtrack(days, costs, i+1, days[i] + 6, memo);
        int dayPass30 = costs[2] + backtrack(days, costs, i+1, days[i] + 29, memo);

        int minCost = Math.min(dayPass1, Math.min(dayPass7, dayPass30));
        memo.put(key, minCost);
        return minCost;
    }

    /*
        Approach#1: BackTracking [TLE]
        Time: O(3 ^ n)
        Space: O(n)
        since n is the number of days
    */
    public int mincostTickets1(int[] days, int[] costs) {
        return traverse(days, costs, 0, 0);
    }

    int traverse(int[] days, int[] costs, int i, int currPassValidity) {
        if(i >= days.length)
            return 0;

        if(days[i] <= currPassValidity)
            return traverse(days, costs, i + 1, currPassValidity); // Skip day if within pass validity

        int dayPass1 = costs[0] + traverse(days, costs, i+1, days[i]);
        int dayPass7 = costs[1] + traverse(days, costs, i+1, days[i] + 6);
        int dayPass30 = costs[2] + traverse(days, costs, i+1, days[i] + 29);

        return Math.min(dayPass1, Math.min(dayPass7, dayPass30));
    }

}
