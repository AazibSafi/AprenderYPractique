package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;

/**
 *  https://leetcode.com/problems/add-digits
 */
public class AddDigits extends BaseTest {

/*
    Time: O(1)
    Space: O(1)
*/
    public int addDigits(int num) {
        if(num == 0)        return 0;
        if(num % 9 == 0)    return 9;
        return num % 9;
    }

/*
    Time: O(logn)
    Space: O(1)
*/
    public int addDigits2(int num) {
        int sum = num;
        while(sum / 10 != 0) {
            num = sum;
            sum = 0;
            while(num != 0) {
                sum += num%10;
                num /= 10;
            }
        }
        return sum;
    }

}
