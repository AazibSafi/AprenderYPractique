package com.algorithms.aprenderypractique.algorithm.arrays.LineSweep.ZeroArrayTransformation;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/zero-array-transformation-i
 *      https://www.youtube.com/watch?v=pZzZKoNM7-0&ab_channel=RoadToFAANG
 */
public class ZeroArrayTransformationI extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(isZeroArray(new int[]{1,0,1}, new int[][]{{0,2}}));
        Assert.assertFalse(isZeroArray(new int[]{4,3,2,1}, new int[][]{{1,3},{0,2}}));
        Assert.assertFalse(isZeroArray(new int[]{4,3,2,1}, new int[][]{{1,3},{0,2},{0,3}}));
    }

/*
    Time: O(Q + N)  -> in worst Case O(N + N) -> O(N)
    Space: O(n)
 */
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] line = new int[n+1];

        // Line Sweeping
        for(int[] query : queries) {
            line[query[0]] += 1;
            line[query[1]+1] -= 1;
        }

        return checkValidity(nums, line);
    }

//  Approach#1
//  We don't need to keep track of cummulative sum in the line array itself, single var will work here
    public boolean checkValidity(int[] nums, int[] line) {
        int currSum = 0;
        for(int i=0; i<nums.length; i++) {
            currSum += line[i];              // Cummulative Sum
            if(currSum < nums[i])         // Check if all elements in nums can be reduced to zero
                return false;
        }
        return true;
    }

//  Approach#2
// We can merge below two loops into one
    public boolean checkValidity2(int[] nums, int[] line) {
        for(int i=1; i<line.length; i++) {
            line[i] += line[i-1];              // Cummulative Sum
            if(line[i-1] < nums[i-1])         // Check if all elements in nums can be reduced to zero
                return false;
        }
        return true;
    }

//  Approach#3
    public boolean checkValidity3(int[] nums, int[] line) {
        // Cummulative Sum
        for(int i=1; i<line.length; i++) {
            line[i] += line[i-1];
            if(line[i-1] < nums[i-1])
                return false;
        }

        // Check if all elements in nums can be reduced to zero.
        for(int i=0; i<nums.length; i++) {
            if(nums[i] - line[i] > 0)
                //if(line[i] < nums[i])
                return false;
        }
        return true;
    }

}
