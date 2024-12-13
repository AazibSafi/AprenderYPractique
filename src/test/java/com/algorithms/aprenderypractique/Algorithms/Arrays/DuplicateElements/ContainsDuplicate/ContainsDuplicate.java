package com.algorithms.aprenderypractique.Algorithms.Arrays.DuplicateElements.ContainsDuplicate;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *      https://leetcode.com/problems/contains-duplicate
 */
public class ContainsDuplicate extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(containsDuplicate(new int[]{1,2,3,1}));
        Assert.assertFalse(containsDuplicate(new int[]{1,2,3,4}));
        Assert.assertTrue(containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }

/*
    Approach#4 - Array Stream Distinct count
    Time: O(n)
    Space: O(1)
    Efficient and Accepted, but only specific to Java language
*/
    public boolean containsDuplicate4(int[] nums) {
        return nums.length != Arrays.stream(nums).distinct().count();
    }

/*
    Approach#3 - Using Set
    Time: O(n)
    Space: O(n)
    Efficient and Accepted
*/
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x : nums) {
            if(set.contains(x))
                return true;
            set.add(x);
        }
        return false;
    }

/*
    Approach#2 - Sorting
    Intuition: If there are any duplicate integers, they will be consecutive after sorting.

    Time: O(nlogn)
    Space: O(1)
*/
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] == nums[i+1])
                return true;
        }
        return false;
    }

/*
    Approach#1 - Naive Linear Search - Brute Force
    Time: O(n^2)
    Space: O(1)
*/
    public boolean containsDuplicate1(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

}
