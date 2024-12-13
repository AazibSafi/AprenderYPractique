package com.algorithms.aprenderypractique.Algorithms.Strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/score-of-parentheses
 *  https://algo.monster/liteproblems/856
 */
public class ScoreOfParentheses extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1, scoreOfParentheses("()"));
        Assert.assertEquals(2, scoreOfParentheses("(())"));
        Assert.assertEquals(2, scoreOfParentheses("()()"));
        Assert.assertEquals(7, scoreOfParentheses("(()()())()"));
        Assert.assertEquals(0, scoreOfParentheses(""));
    }

/*
    Time: O(n)
    Space: O(1)

    1 << depth is equivalent to 2^depth
*/
    public int scoreOfParentheses(String s) {
        int score = 0, depth = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(')
                depth++;
            else {
                depth--;
                if(s.charAt(i-1) == '(')    // if it is a balanced pair
                    score += 1 << depth;
            }
        }
        return score;
    }

}
