package com.algorithms.aprenderypractique.Algorithms.Strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *  https://leetcode.com/problems/swap-adjacent-in-lr-string
 *  https://leetcode.com/discuss/interview-question/1379618/instacart-phone-screen
 *
 *  Logic: https://www.youtube.com/watch?v=3X7tsztReyE
 *  https://leetcode.com/problems/swap-adjacent-in-lr-string/solutions/3462893/solution/?envType=problem-list-v2&envId=7p55wqm
 *
 *  Same Problem: https://leetcode.com/problems/move-pieces-to-obtain-a-string
 *  Same Solution works
 */
public class SwapAdjacentInLRString {

    @ParameterizedTest
    @MethodSource("testCases")
    public void testCanTransform(String start, String target, boolean expected) {
        Assertions.assertEquals(expected, canChange(start, target));
        Assertions.assertEquals(expected, canChange1(start, target));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("RXXLRXRXL", "XRLXXRRLX", true),
                Arguments.of("XXXXXLRXX", "LXXXXXXRX", true),
                Arguments.of("X", "L", false),
                Arguments.of("RXLX", "XXLR", false),
                Arguments.of("XR", "RX", false),
                Arguments.of("XLLXXRXXRX", "LXXXLXXXRR", false)
        );
    }

/*
    Approach#2: Two Pointers
    Time: O(n)
    Space: O(1)
*/
    public boolean canChange(String start, String target) {
        if(start.replace("X", "").length() != target.replace("X", "").length())
            return false;

        int n = start.length(); // both lengths are same

        for(int i = 0, j = 0; i<n || j<n; i++, j++) {

            while(i<n && start.charAt(i) == 'X') {  i++; }
            while(j<n && target.charAt(j) == 'X') {  j++; }

            if (i == n || j == n)   return i == j;

            if(start.charAt(i) != target.charAt(j)
                || (start.charAt(i) == 'L' && i < j)
                || (target.charAt(j) == 'R' && i > j)
            )
                return false;
        }

        return true;
    }

/*
    Approach#1: Using List
    Time: O(n)
    Space: O(n)
*/
    public boolean canChange1(String start, String target) {
        if(start.replace("X", "").length() != target.replace("X", "").length())
            return false;

        //   < 'L' or 'R', Index >
        List<Pair<Character, Integer>> startList = new ArrayList<>();
        List<Pair<Character, Integer>> targetList = new ArrayList<>();

        for(int i=0; i<start.length(); i++) {
            if(start.charAt(i) != 'X') {
                startList.add(Pair.of(start.charAt(i), i));
            }
            if(target.charAt(i) != 'X') {
                targetList.add(Pair.of(target.charAt(i), i));
            }
        }

        for(int i=0; i<startList.size(); i++) {
            char startChar = startList.get(i).getFirst();
            char targetChar = targetList.get(i).getFirst();

            int startIdx = startList.get(i).getSecond();
            int targetIdx = targetList.get(i).getSecond();

            if(startChar != targetChar
                    || (startChar == 'L' && startIdx < targetIdx)
                    || (startChar == 'R' && startIdx > targetIdx)
            )
                return false;
        }

        return true;
    }

}
