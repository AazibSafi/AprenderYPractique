package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/shortest-way-to-form-string
 *
 *      prerequisite:
 *      @see IsSubsequence
 */
public class ShortestWayToFormString extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(2, shortestWay("abc", "abcbc"));
        Assert.assertEquals(-1, shortestWay("abc", "acdbc"));
        Assert.assertEquals(3, shortestWay("xyz", "xzyxz"));
        Assert.assertEquals(3, shortestWay("aaaaa", "aaaaaaaaaaaaa"));
        Assert.assertEquals(11, shortestWay("mnopqrst", "ttttttttttt"));    // Worst Case
    }

/*
    Approach 3: Two Pointer
    Time: O(S + T + T.S) => O(T.S)
    Space: O(1)
 */
    public int shortestWay(String source, String target) {
        if (!isSubsequencePossible(source, target))
            return -1;

        int count = 0;
        int m = source.length(), n = target.length();
        int sourcePtr = 0, targetPtr = 0;

        while (targetPtr < n) { // O(T)
            if (source.charAt(sourcePtr) == target.charAt(targetPtr)) {
                targetPtr++;
            }
            sourcePtr++;

            if(targetPtr == n && sourcePtr < m)
                count++;

            if (sourcePtr == m) {
                sourcePtr = 0; // O(S) => worst case: For each "t" in target, we have to traverse the entire source to find it.
                count++;
            }
        }
        return count;
    }

/*
    Approach 2: Concatenate until Subsequence
    Time: O(S + T + T*(T*S)) => O(T^2*S)
    Space: O(T.S) => concatenate source string T times
 */
    public int shortestWay2(String source, String target) {
        if (!isSubsequencePossible(source, target))
            return -1;

        // Concatenate source until the target is a subsequence of the concatenated
        // string
        String concatenated = source;
        int count = 1;
        while (!isSubsequence(target, concatenated)) { // O(T) => at most T iterations
            concatenated += source; // O(S) => concatenating takes O(source.length) time.
            count++;
        }
        return count;
    }

/*
    Time: O(S+T)
    Space: O(26) -> Constant
 */
    public boolean isSubsequencePossible(String source, String target) {
        boolean[] sChars = new boolean[26];
        for (char c : source.toCharArray()) { // O(S)
            sChars[c - 'a'] = true;
        }
        for (char c : target.toCharArray()) { // O(T)
            if (!sChars[c - 'a'])
                return false;
        }
        return true;
    }

/*
    Time: O(T)
    T be the length of the target string.
    Max(S, T) => it will always T
 */
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        if (m > n)  return false;

        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }

}
