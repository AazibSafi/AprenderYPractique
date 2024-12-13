package com.algorithms.aprenderypractique.Algorithms.Strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *  https://leetcode.com/problems/remove-invalid-parentheses/
 *  https://www.youtube.com/watch?v=Cbbf5qe5stw
 */
public class RemoveInvalidParentheses extends BaseTest {

    @Test
    public void test() {
        String str = "()())()";
        List<String> expectedOutput = Arrays.asList("(())()", "()()()");
        Assert.assertTrue(CommonHelper.isEquals(expectedOutput, removeInvalidParentheses(str)));

        str = "(a)())()";
        expectedOutput = Arrays.asList("(a())()", "(a)()()");
        Assert.assertTrue(CommonHelper.isEquals(expectedOutput, removeInvalidParentheses(str)));

        str = ")(";
        expectedOutput = Collections.singletonList("");
        Assert.assertTrue(CommonHelper.isEquals(expectedOutput, removeInvalidParentheses(str)));

        str = "((()((s((((()";
        expectedOutput = Arrays.asList("(()s)", "()(s)", "()s()");
        Assert.assertTrue(CommonHelper.isEquals(expectedOutput, removeInvalidParentheses(str)));

        str = "x(";
        expectedOutput = Collections.singletonList("x");
        Assert.assertTrue(CommonHelper.isEquals(expectedOutput, removeInvalidParentheses(str)));
    }

    public List<String> removeInvalidParentheses(String s) {
        int minRemoval = getMinRemoval(s);
        Set<String> output = new HashSet<>();
        backtrack(s, minRemoval, output);
        return new ArrayList<>(output);
    }

/**
    Find the minimum number of brackets that can be removed
    Similar logic as
    @see RemoveMinBraces
 */
    int getMinRemoval(String str) {
        Stack<Character> stack = new Stack<>();

        for(char c : str.toCharArray()) {
            if(c == '(')        stack.push(c);
            else if(c == ')') {
                if(stack.isEmpty() || stack.peek() != '(')
                    stack.push(c);
                else
                    stack.pop();
            }
        }
        return stack.size();        // stack contains the unbalanced parentheses
    }

    Set<String> visited = new HashSet<>();      // memoization

    void backtrack(String str, int minRemoval, Set<String> output) {
        String memKey = str+'|'+minRemoval;
        if(visited.contains(memKey))     return;
        visited.add(memKey);

        if(minRemoval == 0) {
            if(isValidParanthesis(str))     output.add(str);
            return;
        }

        for(int i=0; i<str.length(); i++) {
             String subStr = str.substring(0, i) + str.substring(i + 1);
             backtrack(subStr, minRemoval-1, output);
        }
    }

    public boolean isValidParanthesis(String str) {
        int count=0;
        for(char c : str.toCharArray()) {
            if(c == '(')    count++;
            else if(c == ')') {
                if(count == 0)   return false;
                count--;
            }
        }
        return count == 0;
    }

}

