package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    All elements in the array are positive integers
    Find First Duplicate element
 */
public class FirstDuplicateElement extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(1,firstDuplicate(new int[]{2,1,3,5,1,3,2}));
    }
/*
    Time Complexity: O(N)
    Space Complexity: O(1)
 */
    public int firstDuplicate(int[] nums) {
        for(int x : nums) {
            int n = Math.abs(x);
            if(nums[n-1] < 0) return n;
            nums[n-1] = -nums[n-1];
        }
        return -1;
    }

//   O(N) + Space Complexity
    int firstDuplicate2(int[] a) {
        List list = new ArrayList<>();

        for(int x : a) {
            if(list.contains(x)) {
                return x;
            }
            list.add(x);
        }
        return -1;
    }

}
