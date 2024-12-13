package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater extends BaseTest {

    @Test
    public void test() {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        Assert.assertEquals(49, maxArea(height));

        height = new int[]{1,1};
        Assert.assertEquals(1, maxArea(height));

        height = new int[]{0,2};
        Assert.assertEquals(0, maxArea(height));
    }

/*
    Time: O(n)
    Space: O(1)
    Two Pointer Approach
*/
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1;

        int area = 0;
        while(left < right) {
            int currHeight = Math.min(height[left], height[right]);
            int width = right - left;

            area = Math.max(area, currHeight * width);

            if(height[left] <= height[right])
                left++;
            else
                right--;
        }
        return area;
    }

}
