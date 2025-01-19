package com.algorithms.aprenderypractique.Algorithms.Strings.Decode;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 *  https://leetcode.com/problems/decode-string
 *  https://www.youtube.com/watch?v=qB0zZpBJlh8
 */
public class DecodeString extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
        Assert.assertEquals("accaccacc", decodeString("3[a2[c]]"));
        Assert.assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"));
    }


    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()) {
            if(ch == ']') {
                StringBuilder sb = new StringBuilder();
                // Get the encoded string
                while(!stack.isEmpty() && stack.peek() != '[') {
                    sb.insert(0, stack.pop());
                }

                stack.pop();    // pop '['

                StringBuilder k = new StringBuilder();
                // Get the number k, char by char
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k.insert(0, stack.pop());
                }

                // i.e; 2[ab] => abab
                sb.repeat(sb, Integer.parseInt(k.toString()) - 1);

                // Push back the current decoded string into stack
                for(char c : sb.toString().toCharArray())
                    stack.push(c);
            }
            else {
                stack.push(ch);     // Push all the chars into stack except closing bracket ']'
            }
        }

        // Convert the stack chars into a single output String
        return stack.stream().map(String::valueOf).collect(Collectors.joining(""));
    }

}
