package com.algorithms.aprenderypractique.algorithm.greedy;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=2444722699191194
 *  https://leetcode.com/discuss/interview-question/709517/facebook-recruiting-portal-seating-arrangements
 */
public class SeatingArrangements extends BaseTest {

    @Test
    public void testFindMaxValue() {
        int[] arr = new int[]{5, 10, 6, 8};
        Assert.assertEquals(4, minOverallAwkwardness(arr));

        arr = new int[]{1, 2, 5, 3, 7};
        Assert.assertEquals(4, minOverallAwkwardness(arr));
    }

/*
    O(nlogn)
    Because of Sorting
 */
    int minOverallAwkwardness(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int diff = arr[1] - arr[0];

        for(int i = 2; i < n; i++) {
            diff = Math.max(diff, arr[i] - arr[i - 2]);
        }

        return Math.max(diff, arr[n - 1] - arr[n - 2]);
    }

}
