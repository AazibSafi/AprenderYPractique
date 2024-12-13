package com.algorithms.aprenderypractique.Algorithms.Games.JumpGame;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/jump-game
 *
 */
public class JumpGame extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(canJump(new int[]{2, 3, 1, 1, 4}));
        Assert.assertFalse(canJump(new int[]{3, 2, 1, 0, 4}));
        Assert.assertTrue(canJump(new int[]{1}));
        Assert.assertFalse(canJump(new int[]{2,1,0,1,2}));
    }

/*
    Time: O(n)
    Space: O(1)
    Efficient

    https://leetcode.com/problems/jump-game/solutions/20932/6-line-java-solution-in-o-n
 */
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for(int i=0; i<=farthest; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if(farthest >= nums.length-1)   return true;
        }
        return false;
    }

/*
    Time: O(n)
    Space: O(1)
    Efficient

    https://leetcode.com/problems/jump-game/solutions/5130181/video-move-goal-position
    https://www.youtube.com/watch?v=Yan0cv2cLy8
 */
    public boolean canJump2(int[] nums) {
        int goal = nums.length-1;

        for(int i=goal-1; i>=0; i--) {
            if(i + nums[i] >= goal)
                goal = i;
        }

        return goal == 0;
    }

/*
    Time: O(n^2)
    Space: O(n)
    My Own Logic
    Probably Brute Force solution
 */
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        if(n == 1) return true;
        Boolean[] memo = new Boolean[n];
        return jump(nums, 0, memo);
    }

    boolean jump(int[] nums, int i, Boolean[] memo) {
        if(i == nums.length-1) return true;

        if(memo[i] != null) return memo[i];

        for(int k=1; k<=nums[i] && k<nums.length; k++) {
            if(jump(nums, i+k, memo))
                return memo[i] = true;
        }

        return memo[i] = false;
    }

}
