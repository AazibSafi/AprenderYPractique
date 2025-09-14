package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 *  https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards
 *
 *  NOT WORKING SOLUTION
 *  LOOK for Sliding window solution
 */
public class MaximumPointsYouCanObtainFromCards extends BaseTest {

    /*
        Approach: Sliding Window
        Time: O(n) — one pass through k elements
        Space: O(1) — space usage
    */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int totalScore = IntStream.of(cardPoints).sum();
        if (k == n)
            return totalScore;

        // Step 1: Take first k elements
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += cardPoints[i];
        }

        int maxScore = windowSum;

        // Step 2: Slide the window: remove from left-end, add from right-end
        for (int i = 1; i <= k; i++) {
            windowSum -= cardPoints[k-i];   // remove from end of left window
            windowSum += cardPoints[n-i];   // add from end of array
            maxScore = Math.max(maxScore, windowSum);
        }

        return maxScore;
    }

    /*
        Approach: Backtracking [Not working]
        Time: O(n*k)
        Space: O(n*k)
    */
    public int maxScore2(int[] cardPoints, int k) {
        if(cardPoints.length == k)
            return IntStream.of(cardPoints).sum();

        Map<String, Integer> memo = new HashMap<>();
        return backtrack(cardPoints, 0, cardPoints.length-1, k, memo);
    }

    int backtrack(int[] cardPoints, int front, int rear, int k, Map<String, Integer> memo) {
        if(k <= 0)   return 0;

        String key = front+"#"+rear+"#"+k;

        if(memo.containsKey(key))
            return memo.get(key);

        int beginning = cardPoints[front] + backtrack(cardPoints, front+1, rear, k-1, memo);

        int end = cardPoints[rear] + backtrack(cardPoints, front, rear-1, k-1, memo);

        int score = Math.max(beginning, end);
        memo.put(key, score);
        return score;
    }

}
