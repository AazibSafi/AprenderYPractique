package com.algorithms.aprenderypractique.Algorithms.Strings.CandyCrush;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 *      https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string
 */
public class RemoveAllAdjacentDuplicatesInString extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("ca", removeDuplicates("abbaca"));
        Assert.assertEquals("ay", removeDuplicates("azxxzy"));
        Assert.assertEquals("a", removeDuplicates("aabccba"));
        Assert.assertEquals("abcdef", removeDuplicates("abcdef"));
        Assert.assertEquals("a", removeDuplicates("a"));
        Assert.assertEquals("abababababababababab", removeDuplicates("abababababababababab"));
        Assert.assertEquals("", removeDuplicates("aaaaaaaaaa"));
        Assert.assertEquals("", removeDuplicates(""));
    }

/*
    Approach#2 - StringBuilder - [Accepted] - More Efficient Solution
    Time: O(n) => One Pass
    Space: O(n)
*/
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()) {
            if(!sb.isEmpty() && sb.charAt(sb.length()-1) == ch)
                sb.deleteCharAt(sb.length()-1);
            else
                sb.append(ch);
        }
        return sb.toString();
    }

/*
    Approach#1 - Stack - [Accepted]
    Time: O(n + n) => O(n)
    Space: O(n)
*/
    public String removeDuplicates1(String s) {
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek() == ch)
                stack.pop();
            else
                stack.push(ch);
        }
        return stack.stream().toString();
        //return stack.stream().map(String::valueOf).collect(Collectors.joining(""));
    }

}
