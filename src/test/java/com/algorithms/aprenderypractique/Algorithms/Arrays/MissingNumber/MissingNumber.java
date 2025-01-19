package com.algorithms.aprenderypractique.Algorithms.Arrays.MissingNumber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/missing-number
 *  Todo: https://www.geeksforgeeks.org/find-element-appears-array-every-element-appears-twice/#better-solution-using-xor-on-time-and-o1-space
 */
public class MissingNumber extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(2, missingNumber(new int[]{3,0,1}));
        Assert.assertEquals(2, missingNumber(new int[]{0,1}));
        Assert.assertEquals(8, missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));

        System.out.println("0^0: "+ (0^0));
        System.out.println("5^0: "+ (5^0));
        System.out.println("5^5: "+ (5^5));
        System.out.println("5^7: "+ (5^7));
        System.out.println("5^9: "+ (5^9));
        System.out.println("4^7: "+ (4^7));
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int xorA=0, xorB=0;

        for(int i=0; i<=n; i++) {
            xorA ^= i;
        }
        for(int x : nums) {
            xorB ^= x;
        }

        return xorA^xorB;
    }

// Less Efficient
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        return n*(n+1)/2 - Arrays.stream(nums).sum();
    }

    public int missingNumber3(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for(int i=0; i<n; i++) {
            sum += i-nums[i];
        }
        return sum + n;
    }

}
