package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  Pair Sums
 * Given a list of n integers arr[0..(n-1)], determine the number of different pairs of elements within it which sum to k.
 * If an integer appears in the list multiple times, each copy is considered to be different; that is,
 * two pairs are considered different if one pair includes at least one array index which the other doesn't,
 * even if they include the same values.
 */
public class PairSum extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 4, 3};
        Assert.assertEquals(2, numberOfWays(arr,6));

        arr = new int[]{1, 5, 3, 3, 3};
        Assert.assertEquals(4, numberOfWays(arr,6));

        arr = new int[]{1, 5, 3, 3, 3, 5};
        Assert.assertEquals(5, numberOfWays(arr,6));

        arr = new int[]{1, 2, 3, 4, 3};
        Assert.assertEquals(0, numberOfWays(arr,2));
    }

    int numberOfWays(int[] arr, int k) {
        int count = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for(int x : arr) {
            if(map.containsKey(k-x)) {
                count += map.get(k-x);
            }
            map.put(x, map.getOrDefault(x,0)+1);
        }

        return count;
    }

}
