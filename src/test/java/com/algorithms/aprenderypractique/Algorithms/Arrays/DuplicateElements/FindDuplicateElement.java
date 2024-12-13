package com.algorithms.aprenderypractique.Algorithms.Arrays.DuplicateElements;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *  https://leetcode.com/problems/find-the-duplicate-number
 *  https://www.youtube.com/watch?v=dfIqLxAf-8s&ab_channel=TECHDOSE
 *
 *  Find First Duplicate element
 *  Floyd's Cycle Detection
 */
public class FindDuplicateElement extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(2, findDuplicate(new int[]{1,3,4,2,2}));
        Assert.assertEquals(3, findDuplicate(new int[]{3,1,3,4,2}));
        Assert.assertEquals(1, findDuplicate(new int[]{1,1}));
        Assert.assertEquals(1, findDuplicate(new int[]{1,1,2}));
        Assert.assertEquals(2, findDuplicate(new int[]{2,2,2}));
    }

/*
    Approach#4: Floyd's Tortoise and Hare (Cycle Detection)
    Time: O(n)
    Space: O(1)
    This technique will fail if the array starts with 0 element
 */
    public int findDuplicate(int[] nums) {
        if(nums==null || nums.length==0)     return 0;

        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];              //  tortoise:   Move slow pointer by 1
            fast = nums[ nums[fast] ];      //  hare:       Move fast Pointer by 2
        } while(slow != fast);

        // Till now it is detected that the loop exists, Not let's find which exact number is a duplicate

        slow = nums[0];                     //  Reset the pointer to the beginning
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];              //  Move both pointers by 1 now
        }

        return slow;
    }

/*
    Approach#3: Negative Marking
    Time: O(n)
    Space: O(1)
    This Technique works for the integer array with all elements greater than 0
 */
    public int findDuplicate3(int[] nums) {
        int duplicate = -1;
        for(int x : nums) {
            int n = Math.abs(x);
            if(nums[n-1] < 0) {
                duplicate = n;
                break;
            }
            nums[n-1] = -nums[n-1];
        }

        // Restore numbers into original array.
        // Only required if the problem statement has a constraint of not modifying the input array
        for (int i = 0; i < nums.length; i++)
            nums[i] = Math.abs(nums[i]);

        return duplicate;
    }

/*
    Approach#2: Set
    Time: O(n)
    Space: O(n)
 */
    public int findDuplicate2(int[] a) {
        Set<Integer> seen = new HashSet<>();
        for(int x : a) {
            if(seen.contains(x))
                return x;
            seen.add(x);
        }
        return -1;
    }

/*
    Approach#1: Sort
    Time: O(nlogn)
    Space: O(logn) or O(n)
    The space complexity of the sorting algorithm depends on the implementation of each programming language

    Intuition: In a sorted array, duplicate numbers will be next to each other.
 */
    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1])
                return nums[i];
        }
        return -1;
    }

}
