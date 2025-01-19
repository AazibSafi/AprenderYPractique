package com.algorithms.aprenderypractique.Algorithms.Design.MinStack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *      https://leetcode.com/discuss/interview-question/506270/bloomberg-phone-min-order
 *
 *      Similar Problem: https://leetcode.com/problems/min-stack
 *      @see MinStack_1
 *
 *      Approach#1: Stack of Value/ Minimum Pairs
 *      Time Complexity : O(1) for all operations
 *      Space Complexity : O(n)
 */
public class FrontOfficeTrader extends BaseTest {

    @Test
    public void test() {
        FrontOfficeTrader trader = new FrontOfficeTrader();
        trader.addOrder(13);
        trader.addOrder(11);
        trader.addOrder(9);
        trader.addOrder(20);
        Assert.assertEquals(9, trader.extractMinOrder());
        Assert.assertEquals(20, trader.executeOrder());
        Assert.assertEquals(9, trader.extractMinOrder());
        Assert.assertEquals(9, trader.executeOrder());
        Assert.assertEquals(11, trader.extractMinOrder());
        trader.addOrder(11);
        Assert.assertEquals(11, trader.extractMinOrder());
        Assert.assertEquals(11, trader.executeOrder());
        Assert.assertEquals(11, trader.extractMinOrder());
        Assert.assertEquals(11, trader.executeOrder());
        Assert.assertEquals(13, trader.extractMinOrder());
        Assert.assertEquals(13, trader.executeOrder());
    }

    private final Stack<int[]> stack;

    public FrontOfficeTrader() {
        stack = new Stack<>();
    }

    public void addOrder(int amount) {
        int min = stack.isEmpty() ? amount : Math.min(amount, stack.peek()[1]);
        stack.push(new int[]{amount, min});
    }

    // Same as pop of stack
    // Executes an Order (according to the rules above) and returns the amount associated with it
    public int executeOrder() {
        return stack.pop()[0];
    }

    // Returns the amount of the current minimal order, without executing it
    public int extractMinOrder() {
        return stack.peek()[1];
    }
}
