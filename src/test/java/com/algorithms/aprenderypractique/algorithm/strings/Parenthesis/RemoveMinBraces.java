package com.algorithms.aprenderypractique.algorithm.strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *  https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class RemoveMinBraces extends BaseTest {

    @Test
    public void test() {
        String str = "lee(t(c)o)de)";
        Assert.assertEquals("lee(t(c)o)de",minRemoveToMakeValid(str));

        str = "a)b(c)d";
        Assert.assertEquals("ab(c)d",minRemoveToMakeValid(str));

        str = "))((";
        Assert.assertEquals("",minRemoveToMakeValid(str));

        str = "(a(b(c)d)";
        Assert.assertEquals("a(b(c)d)",minRemoveToMakeValid(str));
    }

    public String minRemoveToMakeValid(String str) {
        StringBuilder sb = new StringBuilder();
        int sbIndex=0;
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '(') {
                stack.push(sbIndex);
            }
            else if(str.charAt(i) == ')') {
                if(stack.isEmpty())     continue;
                else stack.pop();
            }
            sb.append(str.charAt(i));
            sbIndex++;
        }

        StringBuilder result = new StringBuilder();
        for(int i=0;i<sb.length();i++) {
            if( !stack.contains(i) )
                result.append(sb.charAt(i));
        }

        return result.toString();
    }

}
