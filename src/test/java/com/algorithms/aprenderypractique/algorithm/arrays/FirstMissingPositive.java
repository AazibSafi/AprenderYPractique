package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/first-missing-positive
 *  https://www.youtube.com/watch?v=9SnkdYXNIzM&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=14
 *
 *  Time Complexity: O(N)
 *  Space complexity: O(1) -- No extra memory
 */
public class FirstMissingPositive extends BaseTest {

    @Test
    public void solution() {
        int[] nums = new int[]
                //{1,2,0};
                //{3,4,-1,1};
                {7,8,9,11,12};
        System.out.println(firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {

        int n = nums.length;
        boolean containsOne = false;

        if(n == 0) return 0;

        for(int i=0;i<n;i++) {
            if(nums[i] == 1)
                containsOne = true;

            if(nums[i] > n || nums[i] <= 0)
                nums[i] = 1;
        }

        if(!containsOne) return 1;

        for(int i=0;i<n;i++) {
            int index = Math.abs(nums[i])-1;
            if(nums[index] > 0) {
                nums[index] = -1*nums[index];
            }
        }

        for(int i=0;i<n;i++) {
            if(nums[i] > 0) {
                return i+1;
            }
        }

        return n+1;
    }

}
