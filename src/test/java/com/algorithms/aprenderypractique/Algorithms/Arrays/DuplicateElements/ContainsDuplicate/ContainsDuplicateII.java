package com.algorithms.aprenderypractique.Algorithms.Arrays.DuplicateElements.ContainsDuplicate;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *      https://leetcode.com/problems/contains-duplicate-ii
 */
public class ContainsDuplicateII extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        Assert.assertTrue(containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        Assert.assertFalse(containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }

/*
    Approach#3 - Using Set of size K
    Time: O(n)
    Space: O(k)
    Sliding Window Technique
*/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if(set.contains(nums[i]))
                return true;

            set.add(nums[i]);

            if(set.size() > k)
                set.remove(nums[i-k]);    // Remove out of window elements
        }
        return false;
    }

/*
    Approach#2 - Using HashMap
    Time: O(n)
    Space: O(k) => Not sure how it is K, it looks like it is O(n)
*/
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(nums[i]) && Math.abs(map.get(nums[i]) - i) <= k)
                return true;
            map.put(nums[i], i);
        }
        return false;
    }

/*
    Approach#1 - Naive Linear Search - Brute Force
    Time Limit Exceeded
    Time: O(n.k)
    Space: O(1)
*/
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<=i+k && j<nums.length; j++) {
                if(nums[i] == nums[j] && Math.abs(i-j) <= k)
                    return true;
            }
        }
        return false;
    }

}
