package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://www.interviewbit.com/problems/add-one-to-number/
 */
public class AddOneToNumber extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {9,9,9};
        Assert.assertArrayEquals(new int[]{1, 0, 0, 0}, addOne(array));

        array = new int[] {2,3,4};
        Assert.assertArrayEquals(new int[]{2, 3, 5}, addOne(array));

        array = new int[] {0,1,2,3};    // Edge Case
        Assert.assertArrayEquals(new int[]{1, 2, 4}, addOne(array));
    }

    public int[] addOne(int[] digits) {
        digits = plusOne(digits);
        return removeLeadingZeroes(digits);     // covering Edge Case to remove initial Zeroes
    }

    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--) {
            if(digits[i]<9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }

    public int[] removeLeadingZeroes(int[] A) {
        int i=0;
        while(i<A.length-1 && A[i]==0) {
            i++;
        }
        return Arrays.copyOfRange(A,i,A.length);
    }

}
