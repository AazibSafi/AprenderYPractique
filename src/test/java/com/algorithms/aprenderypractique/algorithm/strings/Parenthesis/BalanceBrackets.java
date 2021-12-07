package com.algorithms.aprenderypractique.algorithm.strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=211548593612944&c=207085731181336
 *  https://leetcode.com/problems/valid-parentheses/
 */
public class BalanceBrackets extends BaseTest {

    @Test
    public void test() {
        String str = "{[()]}";
        Assert.assertTrue(isValid(str));

        str = "{}()";
        Assert.assertTrue(isValid(str));

        str = "";
        Assert.assertTrue(isValid(str));

        str = null;
        Assert.assertTrue(isValid(str));

        str = "{(})";
        Assert.assertFalse(isValid(str));

        str = ")";
        Assert.assertFalse(isValid(str));
    }

    public boolean isValid(String str) {
        if(StringUtils.isEmpty(str))        return true;

        Map<Character,Character> bracket = new HashMap<>();
        bracket.put('(',')');   bracket.put('[',']');   bracket.put('{','}');

        Stack<Character> stack = new Stack<>();

        for(char c : str.toCharArray()) {
            if(bracket.containsKey(c))        stack.push(c);
            else {
                if(stack.isEmpty() || bracket.get(stack.peek()) != c)
                    return false;
                stack.pop();
            }
        }
        return true;
    }

    @Test
    public void testSingleBracket() {
        String str = "((()))";
        Assert.assertTrue(isValidSingleBraces(str));

        str = "()()";
        Assert.assertTrue(isValidSingleBraces(str));

        str = "";
        Assert.assertTrue(isValidSingleBraces(str));

        str = "))((";
        Assert.assertFalse(isValidSingleBraces(str));

        str = "x(";
        Assert.assertFalse(isValidSingleBraces(str));
    }

/*
    if only one type of brace exist in the string
 */
    public boolean isValidSingleBraces(String str) {
        int open=0;
        for(char c : str.toCharArray()) {
            if(c == '(')    open++;
            else if(c == ')') {
                if(open == 0)   return false;
                open--;
            }
        }
        return open == 0;
    }

}
