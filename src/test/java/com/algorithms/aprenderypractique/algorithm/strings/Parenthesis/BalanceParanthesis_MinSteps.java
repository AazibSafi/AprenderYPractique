package com.algorithms.aprenderypractique.algorithm.strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 *  https://www.youtube.com/watch?v=zNcN5SWkcFY&t=49s
 */
public class BalanceParanthesis_MinSteps extends BaseTest {

    @Test
    public void test() {
        String str = "(()";
        Assert.assertEquals("(())",balanceParanthesis(str));

        str = "))()(";
        Assert.assertEquals("()()()()",balanceParanthesis(str));

        str = "())()((";
        Assert.assertEquals("()()()(())",balanceParanthesis(str));
    }

    public String balanceParanthesis(String str) {
        if(StringUtils.isEmpty(str))    return str;

        StringBuilder paranthesis = new StringBuilder();
        int openedCount = 0, closedCount = 0;
        char open = '(', close = ')';

        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == open)   openedCount++;
            if(str.charAt(i) == close)   closedCount++;

            if(closedCount > openedCount) {
                paranthesis.append(open);
                openedCount++;
            }
            paranthesis.append(str.charAt(i));
        }

        while(openedCount > closedCount) {
            paranthesis.append(close);
            closedCount++;
        }

        return paranthesis.toString();
    }

}
