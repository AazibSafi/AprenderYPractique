package com.algorithms.aprenderypractique.Algorithms.Design.MinStack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *      https://leetcode.com/problems/min-stack
 *      Similar Problem: https://leetcode.com/discuss/interview-question/506270/bloomberg-phone-min-order
 *
 *      Approach#2: Two Stacks
 *      Time Complexity : O(1) for all operations
 *      Space Complexity : O(n)
 *
 *      Intuition
 *      There's another, somewhat different approach to implementing a MinStack. Approach 1 required storing
 *      two values in each slot of the underlying Stack. Sometimes though, the minimum values are very repetitive.
 *      Do we actually need to store the same minimum value over and over again?
 */
public class MinStack_2 extends BaseTest {

    @Test
    public void test() {
        MinStack_2 minStack = new MinStack_2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack_2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek())
            minStack.push(val);
    }

    public void pop() {
        int val = stack.pop();
        if(val == minStack.peek())
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
