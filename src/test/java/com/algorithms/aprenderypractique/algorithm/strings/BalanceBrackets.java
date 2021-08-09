package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=211548593612944&c=207085731181336
 */
public class BalanceBrackets extends BaseTest {

    @Test
    public void test() {
        String str = "{[()]}";
        Assert.assertTrue(areBracketsBalanced(str));

        str = "{}()";
        Assert.assertTrue(areBracketsBalanced(str));

        str = "";
        Assert.assertTrue(areBracketsBalanced(str));

        str = null;
        Assert.assertTrue(areBracketsBalanced(str));

        str = "{(})";
        Assert.assertFalse(areBracketsBalanced(str));

        str = ")";
        Assert.assertFalse(areBracketsBalanced(str));
    }

    public boolean areBracketsBalanced(String str) {
        if(StringUtils.isEmpty(str))    return true;

        Stack<Character> stack = new Stack<>();

        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');   map.put('[',']');   map.put('{','}');

        for(char c : str.toCharArray()) {
            if(map.containsKey(c)) {
                stack.add(c);
            }
            else if(stack.size() > 0) {
                char b = stack.pop();
                if(map.get(b) != c)
                    return false;
            }
            else {
                return false;
            }
        }
        return true;
    }

}
