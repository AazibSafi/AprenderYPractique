package com.algorithms.aprenderypractique.Algorithms.Strings.SlidingWindowAlgorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *     https://leetcode.com/problems/subarrays-with-k-different-integers
 *     https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/4944691/simplified-algorithm-explained-with-visual-example-c-java/
 */
public class SubarraysWithKDifferentIntegers extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(7, subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
        Assert.assertEquals(3, subarraysWithKDistinct(new int[]{1,2,1,3,4}, 3));
    }

/*
    Time: O(n)
    Space: O(k)
 */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k-1);
    }

    public int solve(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int left=0, distinctCount=0;

        for(int right=0; right<nums.length; right++) {
            int x = nums[right];
            map.put(x, map.getOrDefault(x,0) + 1);

            while(map.size() > k) {
                int y = nums[left++];
                map.put(y, map.getOrDefault(y,0) - 1);
                if(map.get(y) == 0)
                    map.remove(y);
            }

            distinctCount += (right - left + 1);     // Size of subarray
        }
        return distinctCount;
    }

}
