package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum.Two_Three_Sum;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see PairSum_NumberOfWays
 * Todo: https://leetcode.com/problems/combination-sum
 *
 * https://www.hackerrank.com/challenges/icecream-parlor
 */
public class PairSum_AllPairs {

    @Test
    public void test() {
        int[] arr = new int[]{5,7,1,2,8,4,3};

        Map<Integer,Integer> result = findSumOfTwoIntegersInArray(arr,10);
        Assert.assertEquals(Map.of(2, 8, 7, 3), result);

        result = findSumOfTwoIntegersInArray(arr,19);       // No 2 values sum up to 19
        Assert.assertEquals(Map.of(), result);

        arr = new int[]{3,2,4};
        result = findSumOfTwoIntegersInArray(arr,6);
        Assert.assertEquals(Map.of(2, 4), result);

        arr = new int[]{1, 5, 3, 3, 3, 5};
        result = findSumOfTwoIntegersInArray(arr,6);
        Assert.assertEquals(Map.of(1, 5, 3, 3), result);
    }

    public Map<Integer, Integer> findSumOfTwoIntegersInArray(int[] arr, int val) {
        Map<Integer, Integer>  result = new HashMap<>();

        List<Integer> list = new ArrayList<>();
        for(int x : arr) {
            Integer remaining = val - x;
            if(list.contains(remaining)) {
                result.put(remaining, x);
            }

            list.add(x);
        }

        return result;
    }

}

