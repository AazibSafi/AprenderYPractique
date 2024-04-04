package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/unique-paths/
 *  https://www.youtube.com/watch?v=4Zq2Fnd6tl0&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 */
public class UniquePaths extends BaseTest {

    @Test
    public void test() {
        int paths = possibleUniquePaths(3,7);
        Assert.assertEquals(28,paths);

        paths = possibleUniquePaths(3,4);
        Assert.assertEquals(10,paths);
    }

/*
    Time Complexity: O(M*N)
    Space Complexity: O(N)

    Space Optimized
 */
    public int possibleUniquePaths(int m, int n) {
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        for(int i=0; i<m-1; i++) {
            for(int j=1; j<n; j++) {
                dp[j] = dp[j] + dp[j-1];
            }
        }

        return dp[n-1];
    }

/*
    Time Complexity: O(M*N)
    Space Complexity: O(M*N)
 */
    public int possibleUniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i==0 || j==0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }

        return dp[m-1][n-1];
    }

}
