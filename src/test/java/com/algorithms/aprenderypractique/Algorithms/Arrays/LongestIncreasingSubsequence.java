package com.algorithms.aprenderypractique.Algorithms.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *      https://leetcode.com/problems/longest-increasing-subsequence
 *
 *      Todo: https://leetcode.com/problems/longest-increasing-subsequence-ii
 *            https://leetcode.com/problems/maximum-height-by-stacking-cuboids
 */
public class LongestIncreasingSubsequence {

    @ParameterizedTest
    @MethodSource("testCases")
    public void testLengthOfLIS(int[] nums, int expected) {
        Assertions.assertEquals(expected, lengthOfLIS(nums));
        Assertions.assertEquals(expected, lengthOfLIS2(nums));
        Assertions.assertEquals(expected, lengthOfLIS3(nums));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 4),
                Arguments.of(new int[]{0, 1, 0, 3, 2, 3}, 4),
                Arguments.of(new int[]{7, 7, 7, 7, 7, 7, 7}, 1),
                Arguments.of(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}, 6),
                Arguments.of(new int[]{2, 5, 1, 8, 3}, 3),
                Arguments.of(new int[]{3, 4, -1, 0, 6, 2, 3}, 4),
                Arguments.of(new int[]{5, 8, 7, 1, 9}, 3),
                Arguments.of(new int[]{3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10}, 6),
                Arguments.of(new int[]{84, 80, 27}, 1),
                Arguments.of(new int[]{1}, 1)
        );
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
                int j = BinarySearch.upperBound(sub, nums[i]);
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
