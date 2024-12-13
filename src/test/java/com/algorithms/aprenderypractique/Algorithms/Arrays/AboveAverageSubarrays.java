package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *  Facebook Recruitment Mock Interview
 *  https://leetcode.com/discuss/interview-question/892956/above-average-subarrays-can-we-do-better-than-o2
 */
public class AboveAverageSubarrays extends BaseTest {

    @Test
    public void test() {
        int[] arr = {3 ,4 ,2};
        Assert.assertArrayEquals(new int[][]{{1,2},{1,3},{2,2}}, aboveAverageSubArrays(arr));
    }

//      Time: O(n^2)
    int[][] aboveAverageSubArrays(int[] arr) {
        int n = arr.length;
        List<int[]> resultList = new ArrayList<>();

        int totalSum = IntStream.of(arr).sum();

        for (int i = 0; i < n; i++) {
            int sumCurrent = 0;

            for (int j = i; j < n; j++) {
                sumCurrent += arr[j];

                int currSubArrayLen = j - i + 1;            // using 1-Based index
                float avgLeft = (float) sumCurrent / currSubArrayLen;
                float avgRight = Integer.MIN_VALUE;     // in case that the whole array is considered in the left subArray, and no element is remaining for right subarray

                if(currSubArrayLen != n) {
                    avgRight = (float) (totalSum - sumCurrent) / (n - currSubArrayLen);
                }

                if (avgLeft > avgRight) {
                    resultList.add(new int[]{i + 1, j + 1});    // Storing 1-Based index
                }
            }
        }

        return resultList.toArray(new int[0][0]);
    }
}
