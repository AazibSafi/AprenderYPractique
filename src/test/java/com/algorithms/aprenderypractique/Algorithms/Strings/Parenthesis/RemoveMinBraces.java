package com.algorithms.aprenderypractique.Algorithms.Strings.Parenthesis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *  https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses
 */
public class RemoveMinBraces {

    @ParameterizedTest
    @MethodSource("testCases")
    public void test(String input, List<String> expected) {
        String result = minRemoveToMakeValid(input);
        assertTrue(expected.contains(result));

        result = minRemoveToMakeValid2(input);
        assertTrue(expected.contains(result));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("lee(t(c)o)de)", List.of("lee(t(c)o)de")),
                Arguments.of("a)b(c)d", List.of("ab(c)d")),
                Arguments.of("))((", List.of("")),
                Arguments.of("(a(b(c)d)", List.of("a(b(c)d)", "(a(bc)d)")),
                Arguments.of("()())()", List.of("()()()")),
                Arguments.of("(((())", List.of("(())")),
                Arguments.of(")(", List.of(""))
        );
    }

/*
    Time: O(n)
    Space: O(n)
 */
    public String minRemoveToMakeValid(String str) {
        Set<Integer> set = new HashSet<>();

        int opened = 0, closed = 0;
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);

            if(c == '(')        opened++;
            else if(c == ')')   closed++;

            if(closed > opened) {
                set.add(i);         // Add unbalanced closed brackets to the set to remove later
                closed--;
            }
        }

        // if closed are greater than open then remove the remaining open braces from the last.
        for(int j=str.length()-1; j>=0 && closed < opened; j--) {
            if(str.charAt(j) == '(') {
                opened--;
                set.add(j);       // Add unbalanced remaining opened brackets to the set to remove later
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            if(!set.contains(i)) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

/*
    Time: O(n)
    Space: O(n)
 */
    public String minRemoveToMakeValid2(String str) {
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

        while(!stack.isEmpty()) {
            builder.deleteCharAt(stack.pop());
        }
        return builder.toString();
    }
}
