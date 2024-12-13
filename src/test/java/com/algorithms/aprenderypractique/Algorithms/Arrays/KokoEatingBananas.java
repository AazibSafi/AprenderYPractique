package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/koko-eating-bananas
 *  https://www.youtube.com/watch?v=ceYZ5RgwQwQ
 */
public class KokoEatingBananas extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(4, minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        Assert.assertEquals(30, minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        Assert.assertEquals(23, minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
        Assert.assertEquals(3, minEatingSpeed(new int[]{805306368, 805306368, 805306368}, 1000000000));
    }

/**
    Time: O(N * log(M))
    Space: O(1)
    Efficient Algo

    Binary Search Upper Bound Logic
    @see UpperAndLowerBound
 */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().getAsInt();

        while(left < right) {                      // O(log(M))
            int mid = left + (right - left)/2;

            if(canEatInTime(piles, h, mid))
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

//  Time: O(N)
    private boolean canEatInTime(int[] piles, int total_hours, int kSpeed) {
        int hoursTaken = 0;
        for(int pile : piles) {
            hoursTaken += (int) Math.ceil(1.0 * pile/kSpeed);
        }
        return hoursTaken <= total_hours;
    }

// Time: O(N * M)
    public int minEatingSpeed2(int[] piles, int h) {
        int maxPile = Arrays.stream(piles).max().getAsInt();

        for(int k=1; k<=maxPile; k++) {         // O(M)
            if(canEatInTime(piles, h, k))       // O(N)
                return k;
        }
        return maxPile;
    }

}
