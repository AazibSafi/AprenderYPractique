package com.algorithms.aprenderypractique.algorithm.arrays.ArraySum.Two_Three_Sum;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/problems/3sum
 *
 *  Also give a thought to use 2 sum problem here.
 *  for each number, use the rest of the array for 2 sum problem. it will give 3 sum solution
 */
public class ThreeSum extends BaseTest {

    @Test
    public void test() {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        // Expected: [[-1,-1,2],[-1,0,1]]

        System.out.println(threeSum(new int[]{0,1,1}));
        // Expected: []

        System.out.println(threeSum(new int[]{0,0,0}));
        // Expected: [[0,0,0]]
    }

/*
    TIme: O(n^2)
    Space: O(n)
 */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);

        for(int i=0; i<n; i++) {
            if(i>0 && nums[i]==nums[i-1]) continue;

            int left = i+1, right = n-1;
            while(left<right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > 0)
                    right--;
                else if(sum < 0)
                    left++;
                else {
                    output.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++; right--;

                    while(left<right && nums[left]==nums[left-1]) left++;
                    while(left<right && nums[right]==nums[right+1]) right--;
                }
            }
        }

        return output;
    }

}