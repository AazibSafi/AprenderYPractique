package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum.Two_Three_Sum;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/two-sum
 */
public class TwoSum extends BaseTest {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{0,1}, twoSum(new int[]{2,7,11,15}, 9));
        Assert.assertArrayEquals(new int[]{1,2}, twoSum(new int[]{3,2,4}, 6));
        Assert.assertArrayEquals(new int[]{0,1}, twoSum(new int[]{3,3}, 6));
    }

/*
    Time: O(n)
    Space: O(n)
 */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(target - nums[i]))
                return new int[]{map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }

        return new int[]{};
    }

}
