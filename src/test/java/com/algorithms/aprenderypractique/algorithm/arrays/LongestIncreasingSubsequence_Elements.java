package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 *  Longest Increasing Subsequence
 *  https://www.interviewbit.com/problems/longest-increasing-subsequence/
 *  https://leetcode.com/problems/longest-increasing-subsequence
 *
 *  https://www.youtube.com/watch?v=CE2b_-XfVDk&t=41s
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestIncreasingSubsequence.java
 */
public class LongestIncreasingSubsequence_Elements extends BaseTest {

    @Test
    public void test() {
        int[] arr = {2, 5, 1, 8, 3};
        Assert.assertTrue(Arrays.asList(2, 5, 8).containsAll(longestIncreasingSubsequence(arr)));

        arr = new int[]{3, 4, -1, 0, 6, 2, 3};
        Assert.assertTrue(Arrays.asList(-1, 0, 2, 3).containsAll(longestIncreasingSubsequence(arr)));

        arr = new int[]{5, 8, 7, 1, 9};
        List<Integer> result = longestIncreasingSubsequence(arr);
        Assert.assertTrue(Arrays.asList(5, 7, 9).containsAll(result) || Arrays.asList(5, 8, 9).containsAll(result));

        arr = new int[]{3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10};
        result = longestIncreasingSubsequence(arr);
        Assert.assertTrue(Arrays.asList(-1, 2, 3, 7, 9, 10).containsAll(result) || Arrays.asList(3, 4, 5, 8, 9, 10).containsAll(result)
                || Arrays.asList(3, 4, 5, 7, 9, 10).containsAll(result));

        arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        result = longestIncreasingSubsequence(arr);
        Assert.assertTrue(Arrays.asList(2, 3, 7, 101).containsAll(result) || Arrays.asList(2, 5, 7, 101).containsAll(result));

        arr = new int[]{0, 1, 0, 3, 2, 3};
        result = longestIncreasingSubsequence(arr);
        Assert.assertTrue(Arrays.asList(0, 1, 2, 3).containsAll(result) || Arrays.asList(2, 5, 7, 101).containsAll(result));

        arr = new int[]{7, 7, 7, 7, 7, 7, 7};
        result = longestIncreasingSubsequence(arr);
        Assert.assertTrue(List.of(7).containsAll(result) || Arrays.asList(2, 5, 7, 101).containsAll(result));

        arr = new int[]{84, 80, 27};
        Assert.assertTrue(Collections.singletonList(84).containsAll(longestIncreasingSubsequence(arr)));

        arr = new int[]{1};
        Assert.assertTrue(Collections.singletonList(1).containsAll(longestIncreasingSubsequence(arr)));
    }

/*
    Time complexity: O(n^2)
    Space complexity: O(n)
    Same logic as LongestIncreasingSubsequence + addition of solution array
 */
    public List<Integer> longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] solution = IntStream.range(0, n).toArray();
        Arrays.fill(dp, 1);      // Each number in the array is itself a subsequence of length 1

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] < nums[j] && dp[i] + 1 > dp[j]) {
                    dp[j] = dp[i] + 1;
                    solution[j] = i;
                }
            }
        }

        List<Integer> list = Arrays.stream(dp).boxed().toList();
        int max = Collections.max(list);
        int iMax = list.indexOf(max);       //  dp[iMax] is the length of longest Increasing Subsequence

        return getTheSubSequence(nums, solution, iMax);
    }

    private List<Integer> getTheSubSequence(int[] arr, int[] solution, int iMax) {
        List<Integer> result = new ArrayList<>();
        int prevI, newI = iMax;

        do {
            prevI = newI;
            result.add(arr[newI]);
            newI = solution[newI];
        } while (prevI != newI);

        return result;
    }

}