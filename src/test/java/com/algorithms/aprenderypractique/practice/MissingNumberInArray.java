package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Arrays;

/**
 *  Find the missing number in the array
 *  an array of positive numbers from 1 to n
 */
public class MissingNumberInArray extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{3,7,1,2,6,4,5};
        System.out.println(findMissingNumber(arr));
    }

//  O(N)
    public int findMissingNumber(int[] arr) {
        int calculateSum = findSum(arr);

        int n = arr.length+1;

        int totalSum = n*(n+1) / 2; // actual sum of all integers from 1 to n

        return totalSum - calculateSum;
    }

    public int findSum(int[] arr) {
        int calculateSum=0;
        for(int x : arr) {
            calculateSum+=x;
        }
        return calculateSum;
    }

}
