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

        str = "()())()";
        Assert.assertEquals("()()()",minRemoveToMakeValid(str));

        str = "(((())";
        Assert.assertEquals("(())",minRemoveToMakeValid(str));

        str = ")(";
        Assert.assertEquals("",minRemoveToMakeValid(str));
    }

    public String minRemoveToMakeValid(String str) {
        StringBuilder builder = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int index = 0;
        for(char c : str.toCharArray()) {
            if(c == '(') {
                stack.push(index);
            }
            else if(c == ')') {
                if(stack.isEmpty())      continue;
                else stack.pop();
            }
            builder.append(c);
            index++;
        }

        StringBuilder result = new StringBuilder();
        for(int i=0; i<builder.length(); i++) {
            if( !stack.contains(i) )
                result.append(builder.charAt(i));
        }

        return result.toString();
    }

}
