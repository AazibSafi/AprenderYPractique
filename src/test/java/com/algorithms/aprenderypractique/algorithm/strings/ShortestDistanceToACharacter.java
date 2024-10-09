package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *      https://leetcode.com/problems/shortest-distance-to-a-character
 *      https://leetcode.com/problems/shortest-distance-to-a-character/solutions/5469332/easy-explanation-3ms
 *
 *      Todo: https://leetcode.com/problems/check-distances-between-same-letters
 */
public class ShortestDistanceToACharacter extends BaseTest {

    @Test
    public void testIndex() {
        Assert.assertArrayEquals(new int[]{3,2,1,0,1,0,0,1,2,2,1,0} , shortestToChar("loveleetcode", 'e'));
        Assert.assertArrayEquals(new int[]{3,2,1,0} , shortestToChar("aaab", 'b'));
    }

/*
    Time: O(n)
    Space: O(n)
 */
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            if(s.charAt(i) == c)
                list.add(i);
        }

        int prevAppearnce = -1, distance;
        int first = 0, last = list.size()-1;

        int[] output = new int[n];
        for(int i=0; i<n; i++) {
            if(s.charAt(i) == c) {
                distance = 0;
                prevAppearnce++;
            }
            else {
                if(i < list.get(first))  distance = list.get(first) - i;
                else if(i > list.get(last))  distance =  i - list.get(last);
                else distance = Math.min(i - list.get(prevAppearnce), list.get(prevAppearnce+1) - i);
            }
            output[i] = distance;
        }
        return output;
    }

}
