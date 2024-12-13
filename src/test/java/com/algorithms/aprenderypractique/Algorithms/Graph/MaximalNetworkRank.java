package com.algorithms.aprenderypractique.Algorithms.Graph;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/maximal-network-rank/
 *  https://www.youtube.com/watch?v=lXiv1sw58d0&ab_channel=NeetCode
 *
 *  Time Complexity: O(N)
 *  Space Complexity: O(N)
 *
 *  Microsoft Hiring Event Question
 */
public class MaximalNetworkRank extends BaseTest {

    @Test
    public void solution() {
        int[][] roads = new int[][]{{0,1},{0,3},{1,2},{1,3}};
        Assert.assertEquals(4, maximalNetworkRank(4,roads));

        roads = new int[][]{{0,1},{0,3},{1,2},{1,3},{2,3},{2,4}};
        Assert.assertEquals(5, maximalNetworkRank(5,roads));

        roads = new int[][]{{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
        Assert.assertEquals(5, maximalNetworkRank(8,roads));
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        int[] count = new int[n];
        int[][] direct = new int[n][n];

        for(int[] road : roads) {
            count[road[0]]++;
            count[road[1]]++;
            direct[road[0]][road[1]] = 1;
            direct[road[1]][road[0]] = 1;
        }

        int rank = 0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                rank = Math.max(rank, count[i] + count[j] - direct[i][j]);
            }
        }

        return rank;
    }

}
