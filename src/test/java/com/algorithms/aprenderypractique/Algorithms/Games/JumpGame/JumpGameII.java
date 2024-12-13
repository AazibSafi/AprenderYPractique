package com.algorithms.aprenderypractique.Algorithms.Games.JumpGame;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/jump-game-ii
 *      https://leetcode.com/problems/jump-game-ii/solutions/5292559/video-keep-near-and-far-position-and-get-the-farthest-position
 *
 *      https://www.youtube.com/watch?v=dJ7sWiOoK7g
 *      https://www.youtube.com/watch?v=KHtghQD0vII&t=43s
 */
public class JumpGameII extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, jump(new int[]{2, 3, 1, 1, 4}));
        Assert.assertEquals(2, jump(new int[]{2, 3, 0, 1, 4}));
        Assert.assertEquals(0, jump(new int[]{1}));
        Assert.assertEquals(2, jump(new int[]{7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3}));
    }

/*
    Time: O(n)
    Space: O(1)
    BFS Solution
 */
    public int jump(int[] nums) {
        int jumpCount = 0;

        int n = nums.length;
        int near = 0, far = 0;

        while(far < n-1) {
            int farthestJump = 0;
            for(int i=near; i<=far; i++) {
                farthestJump = Math.max(farthestJump, i + nums[i]);
            }
            near = far+1;
            far = farthestJump;
            jumpCount++;
        }

        return jumpCount;
    }

}
