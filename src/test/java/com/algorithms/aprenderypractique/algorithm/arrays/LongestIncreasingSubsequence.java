package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *      https://leetcode.com/problems/longest-increasing-subsequence
 *
 *      Todo: https://leetcode.com/problems/longest-increasing-subsequence-ii
 *            https://leetcode.com/problems/maximum-height-by-stacking-cuboids
 */
public class LongestIncreasingSubsequence extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(4, lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        Assert.assertEquals(4, lengthOfLIS(new int[]{0,1,0,3,2,3}));
        Assert.assertEquals(1, lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
        Assert.assertEquals(6, lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
        Assert.assertEquals(3, lengthOfLIS(new int[]{2,5,1,8,3}));
        Assert.assertEquals(4, lengthOfLIS(new int[]{3,4,-1,0,6,2,3}));
        Assert.assertEquals(3, lengthOfLIS(new int[]{5,8,7,1,9}));
        Assert.assertEquals(6, lengthOfLIS(new int[]{3,4,-1,5,8,2,3,12,7,9,10}));
        Assert.assertEquals(1, lengthOfLIS(new int[]{84,80,27}));
        Assert.assertEquals(1, lengthOfLIS(new int[]{1}));
    }

/*
    Time: O(nLogN)
    Space: O(n)
    Approach#4 ->   Improve with Binary Search
*/
    public int lengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for(int i=1; i<nums.length; i++) {
            if(nums[i] > sub.get(sub.size()-1))
                sub.add(nums[i]);
            else {
                //    Binary Search: O(logN)
                //    Finds index of equal or upperbound elm of the target
                int j = UpperAndLowerBound.upperBound(sub, nums[i]);
                sub.set(j, nums[i]);
            }
        }
        return sub.size();
    }

/*
    Time: O(n^2)
    Space: O(n)
    Approach#3 ->  Intelligently Build a Subsequence
        Despite having the same time complexity as the previous approach, in the best and average cases, it is much more efficient.

    Note: This algorithm does not always generate a valid subsequence of the input, but the length of the subsequence will always equal the length of the longest increasing subsequence.
    This approach is suitable when we only want the length as answer, not the subsequence as output.
*/
    public int lengthOfLIS3(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for(int i=1; i<nums.length; i++) {
            if(nums[i] > sub.get(sub.size()-1))
                sub.add(nums[i]);
            else {
                //  find index of Equal or upperbound elm of nums[i]
                int j=0;
                while(sub.get(j) < nums[i]) j++;

                sub.set(j, nums[i]);
            }
        }
        return sub.size();
    }

/*
    Time: O(n^2)
    Space: O(n)
    Approach#2 ->  Dynamic Programming - Tabulation
*/
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(nums[i] < nums[j])
                    dp[j] = Math.max(dp[j], dp[i]+1);
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }

/*
    Time: O(2^n) -> exponential
    Approach#1 ->  Brute Force - Naive solution
*/

}
