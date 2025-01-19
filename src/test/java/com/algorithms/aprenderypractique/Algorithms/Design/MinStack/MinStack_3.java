package com.algorithms.aprenderypractique.Algorithms.Design.MinStack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *      https://leetcode.com/problems/min-stack
 *      Similar Problem: https://leetcode.com/discuss/interview-question/506270/bloomberg-phone-min-order
 *
 *      Approach#3: Improved Two Stacks
 *      Time Complexity : O(1) for all operations
 *      Space Complexity : O(n)
 *
 *      Intuition
 *      In the above approach, we pushed a new number onto the min-tracker Stack if, and only if, it was less than or equal to the current minimum.
 *      One downside of this solution is that if the same number is pushed repeatedly onto MinStack, and that number also happens to be the current minimum,
 *      there'll be a lot of needless repetition on the min-tracker Stack. Recall that we put this repetition in to prevent a bug from occurring (refer to Approach 2).
 */
public class MinStack_3 extends BaseTest {

    @Test
    public void test() {
        MinStack_3 minStack = new MinStack_3();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }

    private Stack<Integer> stack;
    private Stack<int[]> minStack;

    public MinStack_3() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val < minStack.peek()[0])
            minStack.push(new int[]{val, 1});
        else if(val == minStack.peek()[0])
            minStack.peek()[1]++;
    }

    public void pop() {
        int val = stack.pop();
        if(val == minStack.peek()[0]) {
            minStack.peek()[1]--;
            if(minStack.peek()[1] == 0)
                minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek()[0];
    }
}
