package com.algorithms.aprenderypractique.Algorithms.Design.MinStack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *      https://leetcode.com/problems/min-stack
 *      Similar Problem: https://leetcode.com/discuss/interview-question/506270/bloomberg-phone-min-order
 *
 *      Todo Followup Problem: https://leetcode.com/problems/max-stack
 *
 *      Approach#1: Stack of Value/ Minimum Pairs
 *      Time Complexity : O(1) for all operations
 *      Space Complexity : O(n)
 */
public class MinStack_1 extends BaseTest {

    @Test
    public void test() {
        MinStack_1 minStack = new MinStack_1();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }

    private Stack<int[]> stack;

    public MinStack_1() {
        stack = new Stack<>();
    }

    public void push(int val) {
        int min = stack.isEmpty() ? val : Math.min(val, stack.peek()[1]);
        stack.push(new int[]{val, min});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}
