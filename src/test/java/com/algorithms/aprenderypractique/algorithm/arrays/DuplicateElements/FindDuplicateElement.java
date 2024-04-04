package com.algorithms.aprenderypractique.algorithm.arrays.DuplicateElements;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  https://leetcode.com/problems/find-the-duplicate-number/
 *  https://www.youtube.com/watch?v=dfIqLxAf-8s&ab_channel=TECHDOSE
 *
 *  Find First Duplicate element
 *  Floyd's Cycle Detection
 */
public class FindDuplicateElement extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(2,findDuplicate(new int[]{1,3,4,2,2}));
        Assert.assertEquals(3,findDuplicate(new int[]{3,1,3,4,2}));
        Assert.assertEquals(1,findDuplicate(new int[]{1,1}));
        Assert.assertEquals(1,findDuplicate(new int[]{1,1,2}));
        Assert.assertEquals(2,findDuplicate(new int[]{2,2,2}));
    }

/*
    Time: O(n)
    Space: O(1)
 */
    public int findDuplicate(int[] nums) {
        for(int x : nums) {
            int n = Math.abs(x);
            if(nums[n-1] < 0) return n;
            nums[n-1] = -nums[n-1];
        }
        return -1;
    }

/*
    Time: O(n)
    Space: O(1)
    Floyd's Cycle Detection
    This technique will fail if the array starts with 0 element
 */
    public int findDuplicate2(int[] nums) {
        if(nums==null || nums.length==0)     return 0;

        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];              //  Move slow pointer by 1
            fast = nums[ nums[fast] ];      //  Move fast Pointer by 2
        } while(slow != fast);


        slow = nums[0];                     //  Reset the pointer to the beginning
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];              //  Move both pointers by 1 now
        }

        return slow;
    }

//   O(N) + Space Complexity
    int findDuplicate3(int[] a) {
        List<Integer> list = new ArrayList<>();

        for(int x : a) {
            if(list.contains(x)) {
                return x;
            }
            list.add(x);
        }
        return -1;
    }

}
