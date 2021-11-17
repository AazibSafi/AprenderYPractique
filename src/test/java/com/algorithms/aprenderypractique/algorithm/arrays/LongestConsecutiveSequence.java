package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *  https://leetcode.com/problems/longest-consecutive-sequence/
 *  https://www.youtube.com/watch?v=P6RZZMu_maU&ab_channel=NeetCode
 *
 *  Microsoft Hiring Event Question
 */
public class LongestConsecutiveSequence extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(4,longestConsecutive(new int[]{100,4,200,1,3,2}));
        Assert.assertEquals(9,longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

/*
    Time: O(n)
    Space: O(n)
 */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int x : nums)   set.add(x);

        int maxSequence = 0;

        for(int x : nums) {
            if(!set.contains(x-1)) {
                int i=0;
                while(set.contains(x+i))    i++;

                maxSequence = Math.max(maxSequence, i);
            }
        }

        return maxSequence;
    }

}
