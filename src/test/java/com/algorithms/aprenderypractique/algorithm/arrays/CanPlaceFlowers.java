package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.interviewbit.com/problems/flip
 *  https://www.youtube.com/watch?v=K1cv2akL2w8
 *  https://www.youtube.com/watch?v=cLVpE5q_-DE
 */
public class CanPlaceFlowers extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(canPlaceFlowers(new int[]{1,0,0,0,1}, 1));
        Assert.assertFalse(canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
        Assert.assertTrue(canPlaceFlowers(new int[]{0,1,0,0,0,0,0}, 2));
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; n>0 && i < flowerbed.length; i++) {
            boolean left = (i == 0 || flowerbed[i - 1] == 0);
            boolean right = (i == flowerbed.length - 1 || flowerbed[i + 1] == 0);

            if (flowerbed[i] == 0 && left && right) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return n == 0;
    }

}
