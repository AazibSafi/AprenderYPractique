package com.algorithms.aprenderypractique.Algorithms;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Stack;

/**
 *      https://leetcode.com/problems/implement-queue-using-stacks
 */
public class QueueUsingStacks extends BaseTest {

    @Test
    public void test() {
    }

}

class MyQueue {
    Stack<Integer> inputStack, outputStack;

    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public void push(int x) {
        inputStack.push(x);
    }

    public int pop() {
        copyWithReverse(inputStack, outputStack);
        int val = outputStack.pop();
        copyWithReverse(outputStack, inputStack);
        return val;
    }

    public int peek() {
        copyWithReverse(inputStack, outputStack);
        int val = outputStack.peek();
        copyWithReverse(outputStack, inputStack);
        return val;
    }

    public boolean empty() {
        return inputStack.isEmpty();
    }

    void copyWithReverse(Stack<Integer> s1, Stack<Integer> s2) {
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}
