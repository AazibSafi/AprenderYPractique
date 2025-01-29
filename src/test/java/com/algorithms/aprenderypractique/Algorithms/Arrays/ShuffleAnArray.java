package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.Random;

/**
 *      https://leetcode.com/problems/shuffle-an-array
 *      https://www.youtube.com/watch?v=maFZMZaNSec&list=PLX-yaG1hWFMxXuNBxzi3tEPE2SjqbifVp&index=4
 */
public class ShuffleAnArray extends BaseTest {

/*
    Approach: Fisher-Yates Shuffle Algorithm
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
        for(int i=0; i<arr.length; i++) {   // O(n)
            int j = random(i, arr.length);
            swap(arr, i, j);
        }
        return arr;
    }

    private int random(int min, int max) {
        return random.nextInt(max-min)+min;     // O(1)
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
