package com.algorithms.aprenderypractique.algorithm.arrays.Digits.SmallestNumberOfSumOfDigits;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/next-greater-element-iii/
 *  https://www.youtube.com/watch?v=53Rux5-mEDQ&ab_channel=TusharRoy-CodingMadeSimple
 */
public class NextGreaterElement extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(21, nextGreaterElement(12));
        Assert.assertEquals(54812, nextGreaterElement(54281));
        Assert.assertEquals(823512347, nextGreaterElement(823475321));
        Assert.assertEquals(132, nextGreaterElement(123));
        Assert.assertEquals(-1, nextGreaterElement(321));            // No such positive integer exists
        Assert.assertEquals(91999999, nextGreaterElement(19999999));
        Assert.assertEquals(919999999, nextGreaterElement(199999999));
        Assert.assertEquals(-1, nextGreaterElement(1999999999));     // Not fit in 32-bit integer
    }

/*
    Get Char Array from N digits
    Step1: i <- find the index from end of digits that is smaller than its next number
    Step2:  j <- find the index from the end of digits that is greater than the digit of i
        swap digits of i and j
    Step3: reverse the digits from i+1 to the end

    Use try catch to check the constraint of 32-bit integer

    Time Complexity: O(m)
    where m is the number of digits
 */
    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();

        int i = digits.length - 2;  // 2nd last digit

//  Get the number which is greater than n
        while(i>=0 && digits[i] >= digits[i+1]) {
            i--;
        }

//  No such positive integer exists
        if(i == -1)   return -1;

//  Get the smallest number among the greater number we just found above
        int j = digits.length - 1;
        while(j >= i && digits[j] <= digits[i]) {
            j--;
        }

        swap(digits, i , j);

        reverse(digits, i+1);

//  check the constraint of 32-bit integer
        try {
            return Integer.parseInt(new String(digits));
        }
        catch (Exception ex) {
            return -1;
        }
    }

    void reverse(char[] digits, int start) {
        int end = digits.length-1;

        while(start <= end) {
            swap(digits, start, end);
            start++;
            end--;
        }
    }

    void swap(char[] digits, int i, int j) {
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

}
