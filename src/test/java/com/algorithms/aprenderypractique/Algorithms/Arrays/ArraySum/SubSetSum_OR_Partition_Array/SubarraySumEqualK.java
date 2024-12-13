package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum.SubSetSum_OR_Partition_Array;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/subarray-sum-equals-k/
 *  https://www.youtube.com/watch?v=AmlVSNBHzJg&t
 */
public class SubarraySumEqualK extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{1, 1, 1};
        Assert.assertEquals(2, subarraySum(arr, 2));

        arr = new int[]{1, 2, 3};
        Assert.assertEquals(2, subarraySum(arr, 3));

        arr = new int[]{0, 1, -1, 2, -2};
        Assert.assertEquals(6, subarraySum(arr, 0));
    }

    public int subarraySum(int[] nums, int k) {
        if(nums.length == 0)       return 0;

        int result = 0;
        int sum = 0;

        Map<Integer, Integer> map = new HashMap<>();
        //map.put(0,1);

        for(int x : nums) {
            sum += x;

            if(map.containsKey(sum-k)) {
                result += map.get(sum-k);
            }

            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return result;
    }

}
