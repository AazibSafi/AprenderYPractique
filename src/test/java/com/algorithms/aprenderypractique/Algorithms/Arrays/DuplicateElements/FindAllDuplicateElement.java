package com.algorithms.aprenderypractique.Algorithms.Arrays.DuplicateElements;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * @see FindDuplicateElement
 */
public class FindAllDuplicateElement extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(2,3), findDuplicate(new int[]{4,3,2,7,8,2,3,1})));
        Assert.assertTrue(CommonHelper.isEquals(List.of(1), findDuplicate(new int[]{1,1,2})));
        Assert.assertTrue(CommonHelper.isEquals(List.of(), findDuplicate(new int[]{1})));
    }

/*
    Time: O(n)
    Space: O(1)
 */
    public List<Integer> findDuplicate(int[] nums) {
        List<Integer> output = new ArrayList<>();

        for(int x : nums) {
            int n = Math.abs(x);
            if(nums[n-1] < 0) output.add(n);
            nums[n-1] = -nums[n-1];
        }
        return output;
    }

}
