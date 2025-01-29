package com.algorithms.aprenderypractique.Algorithms.Design.RankingPlatform;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *      https://leetcode.com/problems/design-a-leaderboard
 *      https://leetcode.ca/2019-04-27-1244-Design-A-Leaderboard
 *
 *      Similar Problem
 *      @see StockTicker
 *
 *     Where N is the number of players
 *     Time:
 *         addScore: O(logN)
 *         top: O(K)
 *         reset: O(logN)
 *
 *     Space: O(N)
 */
public class Leaderboard {
    Map<Integer, Integer> playerScores;      // <PlayerId, Score>
    TreeMap<Integer, Integer> scoreRank;     // <Score, No of Players>

    // Space: O(N)
    public Leaderboard() {
        this.playerScores = new HashMap<>();
        this.scoreRank = new TreeMap<>(Collections.reverseOrder());
    }

    // Time: O(logN). This is because each addition to the BST takes a logarithmic time for search.
    public void addScore(int playerId, int score) {
        playerScores.merge(playerId, score, Integer::sum);  // Adding old and new Scores
        int newScore = playerScores.get(playerId);
        scoreRank.merge(newScore, 1, Integer::sum);             // increasing the rank of newScore

        if(newScore != score)
            scoreRank.merge(newScore - score, -1, Integer::sum);    // decrease the rank of oldScore
    }

    // Time: O(K). Iterating over top K scores
    public int top(int K) {
        int sumOfTopK = 0;

        for(var rank : scoreRank.entrySet()) {
            int score = rank.getKey();
            int count = rank.getValue();

            count = Math.min(count, K);
            sumOfTopK += count * score;

            K -= count;
            if(K == 0)  break;
        }
        return sumOfTopK; // sum of top K player scores
    }

    // Time: O(logN). Since we need to search for the score in the BST and then update/remove it.
    public void reset(int playerId) {
        int score = playerScores.remove(playerId);
        if(scoreRank.merge(score, -1, Integer::sum) == 0)
            scoreRank.remove(score);
    }

}