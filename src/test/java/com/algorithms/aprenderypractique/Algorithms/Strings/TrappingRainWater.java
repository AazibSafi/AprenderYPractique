package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *      https://leetcode.com/problems/trapping-rain-water
 *      https://www.youtube.com/watch?v=ZI2z5pq0TqA
 */
public class TrappingRainWater extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(6, trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(9, trap(new int[]{4,2,0,3,2,5}));
    }

/*
    Approach#2 - Two Pointer
    Time: O(n)
    Space: O(1)
*/
    public int trap(int[] height) {
        int trappedWater = 0;

        int leftMax = 0, rightMax = 0;
        int left = 0, right = height.length-1;

        while(left < right) {
            if(height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                trappedWater += leftMax - height[left];
                left++;
            }
            else {
                rightMax = Math.max(rightMax, height[right]);
                trappedWater += rightMax - height[right];
                right--;
            }
        }

        return trappedWater;
    }
/*
    Approach#1 - Dynamic Programming
    Time: O(n)
    Space: O(n)
*/
    public int trap1(int[] height) {
        int n = height.length;
        if (height.length == 0) return 0;

        int[] maxLeft = new int[n];
        for(int i=1; i<n; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i-1]);
        }

        int[] maxRight = new int[n];
        for(int i=n-2; i>=0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i+1]);
        }

        // Calculate the trapped water on each block
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if(dp[i] < 0)   dp[i] = 0;  // if it is negative, no water can be trapped
        }

        // Return the total amount of trapped water
        return Arrays.stream(dp).sum();
    }

}
