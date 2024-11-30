package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.Random;

/**
 *      https://leetcode.com/problems/shuffle-an-array
 *      https://www.youtube.com/watch?v=maFZMZaNSec&list=PLX-yaG1hWFMxXuNBxzi3tEPE2SjqbifVp&index=4
 */
public class ShuffleAnArray extends BaseTest {

/*
    Time: O(N)
    Space: O(N)
*/
    int[] nums;
    Random random;

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int[] reset() {
        return this.nums;
    }

    public int[] shuffle() {
        int[] arr = nums.clone();
        for(int i=0; i<arr.length; i++) {
            int j = random(i, arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    private int random(int min, int max) {
        return random.nextInt(max-min)+min;
    }

}
