package com.algorithms.aprenderypractique;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Paranthesis extends BaseTest {

    @Test
    public void solution() {
        Map<Character,Character> map = getValidParenthesis();
        Assert.assertTrue(checkParanthesis("()",map));
        Assert.assertTrue(checkParanthesis("()[]{}",map));
        Assert.assertFalse(checkParanthesis("(]",map));
        Assert.assertFalse(checkParanthesis(")(",map));
        Assert.assertFalse(checkParanthesis("([)]",map));
        Assert.assertTrue(checkParanthesis("{[]}",map));
        Assert.assertFalse(checkParanthesis(")",map));
        Assert.assertFalse(checkParanthesis("([]",map));
        Assert.assertTrue(checkParanthesis("",map));
    }

    private Map<Character,Character> getValidParenthesis() {
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        return map;
    }

    public boolean checkParanthesis(String str, Map<Character,Character> map) {
        boolean valid = true;
        Stack<Character> stack = new Stack<Character>();
        if(CollectionUtils.isEmpty(map) || str == null) {
            valid = false;
        }
        else {
            for(int i=0;i<str.length();i++) {
                Character bracket = str.charAt(i);
                if(map.containsKey(bracket)) {
                    stack.push(bracket);
                }
                else {
                    if(stack.empty() || !(map.get(stack.pop()).equals(bracket))) {
                        valid = false;
                        break;
                    }
                }
            }
            if(!stack.empty()) {
                valid = false;
            }
        }
        return valid;
    }

}
