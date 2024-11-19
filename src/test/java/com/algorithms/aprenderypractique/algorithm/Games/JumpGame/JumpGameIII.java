package com.algorithms.aprenderypractique.algorithm.Games.JumpGame;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *      https://leetcode.com/problems/jump-game-iii
 *      https://leetcode.com/problems/jump-game-iii/solutions/953558/java-dfs-and-bfs-detailed-steps-1-liner
 *
 *      TODO: https://leetcode.com/problems/jump-game-vi
 */
public class JumpGameIII extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));
        Assert.assertTrue(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0));
        Assert.assertFalse(canReach(new int[]{3, 0, 2, 1, 2}, 2));
        Assert.assertTrue(canReach(new int[]{0, 3, 0, 6, 3, 3, 4}, 6));
    }

/*
    Time: O(n)
    Space: O(n)
    DFS Approach
 */
    public boolean canReach2(int[] arr, int start) {
        if(start < 0 || start >= arr.length || arr[start] < 0)
            return false;

        arr[start] *= -1;

        return arr[start] == 0 || canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
    }

/*
    Time: O(n)
    Space: O(n)
    BFS Approach
 */
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            start = queue.poll();

            if(arr[start] == 0)    return true;

            if(arr[start] <  0) // check if visited
                continue;

            if(start + arr[start] < arr.length)
                queue.add(start + arr[start]);

            if(start - arr[start] >= 0)
                queue.add(start - arr[start]);

            arr[start] *= -1;   // mark -ve for visited
        }

        return false;
    }

}
