package com.algorithms.aprenderypractique.Algorithms.Strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *       https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses
 */
public class MaximumNestingDepthOfTheParentheses extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, maxDepth("(1+(2*3)+((8)/4))+1"));
        Assert.assertEquals(3, maxDepth("(1)+((2))+(((3)))"));
        Assert.assertEquals(3, maxDepth("()(())((()()))"));
        Assert.assertEquals(4, maxDepth("( ((X)) (((Y))) )"));
        Assert.assertEquals(4, maxDepth("( a(b) (c) (d(e(f)g)h) I (j(k)l)m)"));
        Assert.assertEquals(3, maxDepth("( p((q)) ((s)t) )"));
        Assert.assertEquals(0, maxDepth(""));
    }

/*
    Approach#2 - Counter variable
    Time: O(n)
    Space: O(1)
    Since input is VALID parentheses string so no need to verify the balancing part of braces
*/
    public int maxDepth(String s) {
        int max = 0, depth = 0;

        for(char c : s.toCharArray()) {
            if(c == '(') {
                depth++;
            }
            else if(c == ')') {
                max = Math.max(max, depth);
                depth--;
            }
        }
        return max;
    }

/*
    Approach#1 - Stack
    Time: O(n)
    Space: O(n)
*/
    public int maxDepth1(String s) {
        Stack<Character> stack = new Stack<>();
        int max = 0;

        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            }
            else if(c == ')' && !stack.isEmpty()) {
                max = Math.max(max, stack.size());
                stack.pop();
            }
        }
        return max;
    }

}
