package com.algorithms.aprenderypractique.algorithm.strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 *  This Problem was asked by Facebook
 *  https://www.youtube.com/watch?v=zNcN5SWkcFY&t=49s
 *
 *  Given a string of parentheses, find the balanced string that can be produced from it
 *  Using the minimum number of insertions and deletions.
 *  If there are multiple solutions, return any of them.
 */
public class BalanceParanthesis_MinSteps extends BaseTest {

    @Test
    public void test() {
        String str = "(()";
        Assert.assertEquals("(())", balanceParenthesis(str));

        str = "))()(";
        Assert.assertEquals("()()()()", balanceParenthesis(str));

        str = "())()((";
        Assert.assertEquals("()()()(())", balanceParenthesis(str));
    }

    public String balanceParenthesis(String str) {
        if(StringUtils.isEmpty(str))    return str;

        StringBuilder parenthesis = new StringBuilder();
        int openedCount = 0, closedCount = 0;
        char open = '(', close = ')';

        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == open)   openedCount++;
            if(str.charAt(i) == close)   closedCount++;

            if(closedCount > openedCount) {
                parenthesis.append(open);
                openedCount++;
            }
            parenthesis.append(str.charAt(i));
        }

        while(openedCount > closedCount) {
            parenthesis.append(close);
            closedCount++;
        }

        return parenthesis.toString();
    }

}
