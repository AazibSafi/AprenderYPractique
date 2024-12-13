package com.algorithms.aprenderypractique.Algorithms.Arrays.MonotonicStack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 *  Asked in Amazon Similar Question
 *  https://leetcode.com/discuss/interview-question/1639758/amazon-oa-usa
 *  Code Question 2 : ShipmentImbalance
 *
 *  https://leetcode.com/problems/sum-of-subarray-ranges/
 *
 *  The Solution has the subproblem of Sum Of Subarray Miniumum
 **/
public class SubArrayRanges extends BaseTest {

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        Assert.assertEquals(4, subArrayRanges(nums));

        nums = new int[]{1, 3, 3};
        Assert.assertEquals(4, subArrayRanges(nums));

        nums = new int[]{4, -2, -3, 4, 1};
        Assert.assertEquals(59, subArrayRanges(nums));

        nums = new int[]{3, 3, 2, 3};
        Assert.assertEquals(5, subArrayRanges(nums));
    }

    /*
        Time: O(n^2)
        Space: O(n)
     */
    public long subArrayRanges_NonOptimal(int[] nums) {
        long ans = 0;
        for(int i=0; i<nums.length; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int j=i; j<nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += (max - min);
            }
        }
        return ans;
    }

    /*
        Time: O(n)
        Space: O(n)

        Solution is (Sum of Subarray Max) - (Sum of Subarray Min)
     */
    public long subArrayRanges_2(int[] nums) {
        return new SumOfSubarrayMaximums().sumSubarrayMaxs(nums) - new SumOfSubarrayMinimums().sumSubarrayMins(nums);
    }

    /*
        Same logic but Combining two solutions in a single loop
     */
    public long subArrayRanges(int[] nums) {
        long result = 0;

        int[] NSL = new SumOfSubarrayMinimums().getNextSmallerToLeft(nums);
        int[] NSR = new SumOfSubarrayMinimums().getNextSmallerToRight(nums);
        int[] NGL = new SumOfSubarrayMaximums().getNextGreaterToLeft(nums);
        int[] NGR = new SumOfSubarrayMaximums().getNextGreaterToRight(nums);

        long sum;
        for(int i=0; i<nums.length; i++) {
            long ls = i - NSL[i];
            long rs = NSR[i] - i;
            long lg = i - NGL[i];
            long rg = NGR[i] - i;
            sum = ((lg*rg) - (ls*rs)) * nums[i];

            result += sum;
        }

        return (int) result;
    }

}
