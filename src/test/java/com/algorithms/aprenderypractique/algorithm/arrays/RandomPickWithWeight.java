package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.Random;

/**
 *      https://leetcode.com/problems/random-pick-with-weight
 *
 *      https://www.youtube.com/watch?v=fWS0TCcr-lE
 *      https://www.youtube.com/watch?v=7x7Ydq2Wfvw
 *
 *      Time: O(N + LogN)
 *      Space: O(N)
 */
public class RandomPickWithWeight extends BaseTest {
    int[] preSum;

    public void init(int[] w) {
        preSum = new int[w.length];
        preSum[0] = w[0];
        for(int i=1; i<w.length; i++) {     // O(N)
            preSum[i] = preSum[i-1] + w[i];
        }
    }

    public int pickIndex() {
        int n = preSum.length;
        if (preSum[n-1] == 0) return -1;  // No numbers to pick!

        int key = new Random().nextInt(preSum[n-1]) + 1; // 0 excluded
        return UpperAndLowerBound.upperBound(preSum, key);
    }

}
