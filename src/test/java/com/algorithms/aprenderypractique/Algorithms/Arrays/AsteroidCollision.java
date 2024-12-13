package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *      https://leetcode.com/problems/asteroid-collision
 *      https://www.youtube.com/watch?v=5AV33YdtDYw
 */
public class AsteroidCollision extends BaseTest {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{5,10}, asteroidCollision(new int[]{5,10,-5}));
        Assert.assertArrayEquals(new int[]{}, asteroidCollision(new int[]{8,-8}));
        Assert.assertArrayEquals(new int[]{10}, asteroidCollision(new int[]{10,2,-5}));
        Assert.assertArrayEquals(new int[]{-2, -1, 1, 2}, asteroidCollision(new int[]{-2, -1, 1, 2}));
        Assert.assertArrayEquals(new int[]{-16}, asteroidCollision(new int[]{10, 2, -5, 8, -16}));
    }

/*
    Time: O(N)
    Space: O(N)
*/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for(int a : asteroids) {
            if(a > 0) {
                stack.push(a);
            }
            else {
                while(!stack.isEmpty() && stack.peek()>0 && stack.peek()<Math.abs(a)) {
                    stack.pop();
                }

                if(stack.isEmpty() || stack.peek() < 0) {
                    stack.push(a);
                }
                else if(stack.peek() == Math.abs(a)) {
                    stack.pop();
                }
            }
        }

        int n = stack.size();
        int[] output = new int[n];
        while(!stack.isEmpty()) {
            output[--n] = stack.pop();
        }

        return output;
    }

}
