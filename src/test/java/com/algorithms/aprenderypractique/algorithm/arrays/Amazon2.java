package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/discuss/interview-question/1639758/amazon-oa-usa
 *  Code Question 2
 *
 *  Similar: https://leetcode.com/problems/sum-of-subarray-ranges/
 *  @see SubArrayRanges
 */
public class Amazon2 extends BaseTest {

    @Test
    public void test1() {
        List<Integer> weight = Arrays.asList(1,2,3);
        Assert.assertEquals(4, getTotalImbalance(weight));

        weight = Arrays.asList(3, 3, 2, 3);
        Assert.assertEquals(3, getTotalImbalance(weight));

        weight = Arrays.asList(1,3,3);
        Assert.assertEquals(4, getTotalImbalance(weight));
    }

    public long getTotalImbalance(List<Integer> weight) {
        int sum = 0;
        for(int i = 0; i < weight.size(); i++) {
            for(int j = i; j < weight.size(); j++) {
                sum += Math.abs(weight.get(j) - weight.get(i));
            }
        }
        return sum;
    }

}
