package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see PairSum
 */
public class SumOfPairInArray extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{5,7,1,2,8,4,3};
        Map<Integer,Integer> result = findSumOfTwoIntegersInArray(arr,10);

        Assert.assertEquals(ImmutableMap.<Integer, Integer>builder().put(2, 8).put(7, 3).build(), result);

        result = findSumOfTwoIntegersInArray(arr,19);       // No 2 values sum up to 19
        Assert.assertEquals(ImmutableMap.<Integer, Integer>builder().build(), result);
    }

    public Map<Integer,Integer> findSumOfTwoIntegersInArray(int[] arr, int val) {
        Map<Integer,Integer>  result = new HashMap<>();
        Map<Integer,Integer>  tableCheck = new HashMap<>();

        for(int x : arr) {
            Integer remaining = val - x;
            if(tableCheck.containsKey(x)) {
                result.put(remaining, x);
            }

            tableCheck.put(remaining, x);
        }

        return result;
    }

}
