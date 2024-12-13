package com.algorithms.aprenderypractique.Algorithms.Strings.Math;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *  https://leetcode.com/problems/evaluate-reverse-polish-notation
 */
public class EvaluateReversePolishNotation extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(9, evalRPN(new String[]{"2","1","+","3","*"}));
        Assert.assertEquals(6, evalRPN(new String[]{"4","13","5","/","+"}));
        Assert.assertEquals(22, evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }

/*
    Time: O(n)
    Space: O(n)
 */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        String operators = "+-*/";

        for(String token : tokens) {
            if(operators.contains(token)) {
                int b = stack.pop(), a = stack.pop();
                stack.push( perform(a, b, token) );
            }
            else
                stack.push( Integer.parseInt(token) );
        }
        return stack.pop();
    }

    int perform(int a, int b, String operator) {
        int result = 0;
        if("+".equals(operator))         result = a + b;
        else if("-".equals(operator))    result = a - b;
        else if("*".equals(operator))    result = a * b;
        else if("/".equals(operator))    result = a / b;
        return result;
    }

}
