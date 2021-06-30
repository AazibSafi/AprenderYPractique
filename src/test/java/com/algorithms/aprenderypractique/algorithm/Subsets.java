package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a set of distinct integers, nums, return all possible subsets (The power Set)
 * Note: The solution set must not contain duplicate subsets
 *
 * https://www.youtube.com/watch?v=LdtQAYdYLcE
 */
public class Subsets extends BaseTest {

    @Test
    public void test() {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> subsets = findSubsets(nums);
        print2DList(subsets);
        System.out.println(subsets.size());

        Assert.assertTrue(subsets.equals(
                Arrays.asList(
                        Arrays.asList(),
                        Arrays.asList(1),
                        Arrays.asList(2),
                        Arrays.asList(3),
                        Arrays.asList(1,2),
                        Arrays.asList(1,3),
                        Arrays.asList(2,3),
                        Arrays.asList(1,2,3)
                )
        ));
    }

/*
*   O(N * 2 to the power n)
*/
    public List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new LinkedList<>();
        generateSubsets(0,nums,new LinkedList<>(),subsets);
        return subsets;
    }

    public void generateSubsets(int index, int[] nums, List<Integer> curr, List<List<Integer>> subsets) {
        subsets.add(new LinkedList<>(curr));

        for(int i=index;i<nums.length;i++) {
            curr.add(nums[i]);
            generateSubsets(i+1,nums,curr,subsets);
            curr.remove(curr.size()-1);
        }
    }

    public void print2DList(List<List<Integer>> list) {
        list.forEach( nestedList -> {
            Object str = nestedList.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println("[" + str + "]");
        });
    }

}
