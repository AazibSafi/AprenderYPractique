package com.algorithms.aprenderypractique.Algorithms.Arrays.MissingNumber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 *  https://leetcode.com/problems/first-missing-positive
 *  https://www.youtube.com/watch?v=9SnkdYXNIzM&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 *
 *  Time Complexity: O(N)
 *  Space complexity: O(1) -- No extra memory
 */
public class FirstMissingPositive extends BaseTest {

    @Test
    public void solution() {
        int[] nums = new int[]{1,2,0};
        Assert.assertEquals(3,firstMissingPositive(nums));

        nums = new int[]{3,4,-1,1};
        Assert.assertEquals(2,firstMissingPositive(nums));

        nums = new int[]{7,8,9,11,12};
        Assert.assertEquals(1,firstMissingPositive(nums));

        nums = new int[]{2, 7, 11, 15};
        Assert.assertEquals(1,firstMissingPositive(nums));

        nums = new int[]{};
        Assert.assertEquals(1,firstMissingPositive(nums));

        nums = new int[]{1};
        Assert.assertEquals(2,firstMissingPositive(nums));
    }

    int firstMissingPositive(int[] nums) {
        if(nums.length == 0 || IntStream.of(nums).noneMatch(x -> x==1))
            return 1;       // Step - 1 -- if there is any element with value 1

// step 1 and 2 can be done together in single iteration

        markOne_ForAllNegativeAndOutOfRangeElements(nums);      // Step - 2

        markEachElementVisited(nums);       // Step - 3

        int firstPositiveElementIndex = findFirstPositiveElement(nums);     //  Step - 4

        if(firstPositiveElementIndex != -1)
            return firstPositiveElementIndex;

//  if no positive element found, that means all numbers from 1 to N are available in the array
//  Therefore, only N+1 number is the missing
        return nums.length+1;
    }

//      Marking with 1, for all elements in the array that are negative and greater than the N
    void markOne_ForAllNegativeAndOutOfRangeElements(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            if(nums[i] <= 0 || nums[i] > nums.length)
                nums[i] = 1;
        }
    }

//      Visit each element and mark it visited by marking the negative sign on [ (nums of index) - 1] element
    void markEachElementVisited(int[] nums) {
        for(int i=0;i<nums.length;i++) {
            int index = Math.abs(nums[i])-1;
            if(nums[index] > 0) {
                nums[index] = -1 * nums[index];
            }
        }
    }

//      Find first positive number in the array, That index+1 is the missing number
    int findFirstPositiveElement(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0) {
                return i+1;
            }
        }
        return -1;
    }

}
