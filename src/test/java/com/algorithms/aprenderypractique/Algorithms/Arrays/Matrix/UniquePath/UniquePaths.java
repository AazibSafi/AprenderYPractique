package com.algorithms.aprenderypractique.Algorithms.Arrays.Matrix.UniquePath;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/unique-paths
 *  https://www.youtube.com/watch?v=4Zq2Fnd6tl0&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 */
public class UniquePaths extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(28, uniquePaths(3,7));
        Assert.assertEquals(10, uniquePaths(3,4));
        Assert.assertEquals(86493225, uniquePaths(19,13));
    }

/*
    Time: O(N*M)
    Space: O(N) -- 1d Array
 */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        for(int i=0; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }

/*
    Time: O(N*M)
    Space: O(N*M) -- 2d Array
 */
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);      // First Row
        for(int[] row : dp)
            row[0] = 1;             // First Col

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

/*
    Time: O(N*M)
    Space: O(N*M) -- Recursive Stack
 */
    public int uniquePaths3(int m, int n) {
        int[][] mem = new int[m][n];
        for(int[] row : mem)
            Arrays.fill(row, -1);
        return paths(m-1, n-1, mem);
    }

    public int paths(int m, int n, int[][] mem) {
        if(m==0 && n==0) return 1;
        if(m<0 || n<0) return 0;

        if(mem[m][n] != -1) return mem[m][n];

        return mem[m][n] = paths(m-1, n, mem) + paths(m, n-1, mem);
    }

}
