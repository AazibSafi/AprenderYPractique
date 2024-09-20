package com.algorithms.aprenderypractique.algorithm.arrays;

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

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while(left < right) {
            int width = right - left;
            int hgt;
            if(height[left] < height[right])
                hgt = height[left++];
            else
                hgt = height[right--];

            maxArea = Math.max(maxArea, width*hgt);
        }
        return maxArea;
    }

}
