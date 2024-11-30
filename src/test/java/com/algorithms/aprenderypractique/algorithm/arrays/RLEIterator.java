package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;

/**
 *  https://leetcode.com/problems/rle-iterator
 *  https://www.youtube.com/watch?v=49vAm5d8W2k
 */
public class RLEIterator extends BaseTest {
    int[] encoding;
    int index;

    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        index = 0;
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public int next(int n) {
        while(index < encoding.length && n > encoding[index]) {
            n -= encoding[index];
            encoding[index] = 0;
            index += 2;
        }

        if(index >= encoding.length) return -1;

        encoding[index] -= n;
        return encoding[index+1];
    }

}
