package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *  Longest Increasing Subsequence
 *  https://www.interviewbit.com/problems/longest-increasing-subsequence/
 *
 *  https://www.youtube.com/watch?v=CE2b_-XfVDk&t=41s
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestIncreasingSubsequence.java
 *
 *     Time complexit: O(n^2).
 *     Space complexity: O(n)
 */
public class LongestIncreasingSubsequence extends BaseTest {

    @Test
    public void test() {
        int[] arr = {2,5,1,8,3};
        Assert.assertTrue(Arrays.asList(2,5,8).containsAll(longestIncreamentSubsequence(arr)));

        arr = new int[]{3,4,-1,0,6,2,3};
        Assert.assertTrue(Arrays.asList(-1,0,2,3).containsAll(longestIncreamentSubsequence(arr)));

        arr = new int[]{5,8,7,1,9};
        List result = longestIncreamentSubsequence(arr);
        Assert.assertTrue(Arrays.asList(5,7,9).containsAll(result) || Arrays.asList(5,8,9).containsAll(result));

        arr = new int[]{3,4,-1,5,8,2,3,12,7,9,10};
        result = longestIncreamentSubsequence(arr);
        Assert.assertTrue(Arrays.asList(-1,2,3,7,9,10).containsAll(result) || Arrays.asList(3,4,5,8,9,10).containsAll(result)
                    || Arrays.asList(3,4,5,7,9,10).containsAll(result));

        arr = new int[]{10,9,2,5,3,7,101,18};
        result = longestIncreamentSubsequence(arr);
        Assert.assertTrue(Arrays.asList(2,3,7,101).containsAll(result) || Arrays.asList(2,5,7,101).containsAll(result));

        arr = new int[]{84,80,27};
        Assert.assertTrue(Arrays.asList(84).containsAll(longestIncreamentSubsequence(arr)));

        arr = new int[]{1};
        Assert.assertTrue(Arrays.asList(1).containsAll(longestIncreamentSubsequence(arr)));
    }

/*
    Time complexit: O(n^2)
    Space complexity: O(n)
 */
    public List<Integer> longestIncreamentSubsequence(int[] arr) {
        int[] dp = new int[arr.length];
        int[] solution = IntStream.rangeClosed(0, arr.length-1).toArray();

        Arrays.fill(dp,1);      // Each number in the array is itself a subsequence of length 1

        dynamicProgrammingLogic(arr,dp,solution);

        int iMax = getMaxElementIndex(dp);

//      dp[iMax] is the length of longest Increasing Subsequence

        return getTheSubSequence(arr,solution,iMax);
    }

    private void dynamicProgrammingLogic(int[] arr, int[] dp, int[] solution) {
        for(int i=1; i<arr.length; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i] && dp[j]+1 > dp[i] ) {
                    dp[i] = dp[j] + 1;
                    solution[i] = j;
                }
            }
        }
    }

    private List<Integer> getTheSubSequence(int[] arr, int[] solution, int iMax) {
        List<Integer> result = new ArrayList<>();
        int prevI, newI = iMax;

        do {
            prevI = newI;
            result.add(arr[prevI]);
            newI = solution[prevI];
        } while(prevI != newI);

        return result;
    }

    private int getMaxElementIndex(int[] T) {
        int iMax = 0;
        for(int i=0; i<T.length; i++) {
            if(T[i] > T[iMax]) {
                iMax = i;
            }
        }
        return iMax;
    }

}
