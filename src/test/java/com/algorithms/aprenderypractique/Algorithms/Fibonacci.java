package com.algorithms.aprenderypractique.Algorithms;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

public class Fibonacci extends BaseTest {

    @Test
    public void test() {

    }

//  Dynamic Programming Approach - Non-Recursive
    public int CalculateFibonacci(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i=2; i<=n; i++)
            dp[i] = dp[i-1] + dp[i-2];

        return dp[n];
    }

//  O(2 to power k)
    public int fab(int n) {
        if(n == 0 || n == 1) return n;
        return fab(n-1) + fab(n-2);
    }

//  O(K)
// optimize than previous solution
// Using cache or memoization
    public int fibonacci(int n, Integer[] mem) {
        if(n == 0 || n == 1) return n;
        if(mem[n] != null)  return mem[n];
        return mem[n] = fibonacci(n - 1, mem) + fibonacci(n - 2, mem);
    }

}
