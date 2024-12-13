package com.algorithms.aprenderypractique.Algorithms.Greedy;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=838749853303393
 *      https://leetcode.com/discuss/interview-question/848430/element-swapping-facebook-coding-practice-2020
 */
public class ElementSwapping extends BaseTest {

    @Test
    public void testFindMaxValue() {
        int[] arr = new int[]{5, 3, 1};
        Assert.assertArrayEquals(new int[]{1, 5, 3}, findMinArray(arr,2));

        arr = new int[]{8, 9, 2, 11, 1};
        Assert.assertArrayEquals(new int[]{2, 8, 9, 1, 11}, findMinArray(arr,3));
    }

    public int[] findMinArray(int[] arr, int k) {
        for(int i=0; i<arr.length && k>0; i++) {

            int minElmInd = findMinDistance(arr,i,k);

            swapElements(arr,i,minElmInd);

            k -= minElmInd - i;
        }
        return arr;
    }

    private int findMinDistance(int[] arr, int start, int k) {
        int min = start;
        for(int i=start; i<=start+k; i++) {
            if(arr[i] < arr[min]) {
                min=i;
            }
        }
        return min;
    }

    private void swapElements(int[] arr, int start, int end) {
        while(end>start) {
            int temp = arr[end];
            arr[end] = arr[end-1];
            arr[end-1] = temp;
            end--;
        }
    }

}
