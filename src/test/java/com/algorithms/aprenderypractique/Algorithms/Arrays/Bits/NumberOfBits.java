package com.algorithms.aprenderypractique.Algorithms.Arrays.Bits;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOfBits extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, hammingWeight(11));
        Assert.assertEquals(1, hammingWeight(128));
        Assert.assertEquals(30, hammingWeight(2147483645));
    }

/*
    Logic:
        The number of 1 bits in N integer = (n/2 + n%2)
        if the number is odd, then the number of 1 bits in N would 1 plus it's half number
        if the number is Even, then number of 1 bits in N would be same as it's half number.

        Here n%2 will only give O (for Even) or 1 (for Odd)
 */

/*
    Time: O(logN)
    Space: O(1)
*/
    public int hammingWeight(int n) {
        int count = 0;
        while(n>0) {
            count += n%2;
            n/=2;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        while(n>0) {
            if(n%2==1)
                count++;
            n/=2;
        }
        return count;
    }

/*
    Time: O(n)
    Space: O(n)
*/
    public int hammingWeight_Recursive(int n) {
        if(n==0 || n==1) return n;
        return hammingWeight(n/2) + n%2;
    }

//  No builtin function is Acceptable
//  This is just for understanding the function
    public int hammingWeight_builtInMethod(int n) {
        return Integer.bitCount(n);
    }

}
