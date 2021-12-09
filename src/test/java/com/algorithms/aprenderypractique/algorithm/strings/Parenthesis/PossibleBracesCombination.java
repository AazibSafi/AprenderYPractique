package com.algorithms.aprenderypractique.algorithm.strings.Parenthesis;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.educative.io/m/all-possible-braces
 * https://www.youtube.com/watch?v=s9fokUqJ76A
 */
public class PossibleBracesCombination extends BaseTest {

    @Test
    public void test() {
        System.out.println(findBraceCombinations(3));
    }

/*
    One Brace combination size would be n*(Bsize)
    Bsize -> bracket size -> 2 -> []
    Time Complexity: O(2 to the power n)
    Memory Complexity: O(n)
 */
    public List<String> findBraceCombinations(int n) {
        List<String> combinations = new ArrayList<>();
        possibleBraces(n,0,0, new StringBuilder(), combinations);
        return combinations;
    }

    public void possibleBraces(int n, int open, int close, StringBuilder currBrace, List<String> combinations) {

        if(open>=n && close>=n) {
            combinations.add(currBrace.toString());
            return;
        }

        if(open < n) {
            currBrace.append("[");
            possibleBraces(n,open+1,close,currBrace,combinations);
            currBrace.deleteCharAt(currBrace.length()-1);
        }

        if(open > close) {
            currBrace.append("]");
            possibleBraces(n,open,close+1,currBrace,combinations);
            currBrace.deleteCharAt(currBrace.length()-1);
        }

    }

}
