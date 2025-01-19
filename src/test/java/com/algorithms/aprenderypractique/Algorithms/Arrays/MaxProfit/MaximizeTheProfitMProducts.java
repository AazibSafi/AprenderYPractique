package com.algorithms.aprenderypractique.Algorithms.Arrays.MaxProfit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *       https://www.geeksforgeeks.org/find-maximum-profit-for-products-within-budget
 */
public class MaximizeTheProfitMProducts {

    @ParameterizedTest
    @MethodSource("testCases")
    public void testMaximumProfitProduct(int expected, int M, int[] costPrices, int[] sellingPrices) {
        Assertions.assertEquals(expected, maximizeProfit1(M, costPrices, sellingPrices));
        Assertions.assertEquals(expected, maximizeProfit2(M, costPrices, sellingPrices));
        Assertions.assertEquals(expected, maximizeProfit3(M, costPrices, sellingPrices));
        Assertions.assertEquals(expected, maximizeProfit4(M, costPrices, sellingPrices));
        Assertions.assertEquals(expected, maximizeProfit5(M, costPrices, sellingPrices));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(8, 3,
                        new int[]{5, 10, 35, 7, 23},
                        new int[]{11, 10, 0, 9, 19}
                ),
                Arguments.of(7, 2,
                        new int[]{17, 9, 8, 20},
                        new int[]{10, 9, 8, 27}
                ),
                Arguments.of(6, 3,   // Fewer products than the budget (M > N)
                        new int[]{5, 10},
                        new int[]{11, 10}
                ),
                Arguments.of(0, 3,   // No products to buy (N = 0)
                        new int[]{},
                        new int[]{}
                ),
                Arguments.of(0, 0,   // No budget to buy any product (M = 0)
                        new int[]{5, 10, 35, 7, 23},
                        new int[]{11, 10, 0, 9, 19}
                ),
                Arguments.of(0, 2,   // All products have negative profit
                        new int[]{10, 20, 30},
                        new int[]{5, 15, 25}
                ),
                Arguments.of(0, 2,   // All products have zero profit
                        new int[]{10, 20, 30},
                        new int[]{10, 20, 30}
                ),
                Arguments.of(5, 1,   // Single product with positive profit
                        new int[]{10},
                        new int[]{15}
                ),
                Arguments.of(0, 1,   // Single product with negative profit
                        new int[]{10},
                        new int[]{5}
                ),
                Arguments.of(10, 2,  // All products have the same profit
                        new int[]{10, 10, 10, 10},
                        new int[]{15, 15, 15, 15}
                ),
                Arguments.of(9, 3,
                        new int[]{10, 10, 10},
                        new int[]{16, 10, 13}
                )
        );
    }

    public int[] calculateProductProfits(int[] cp, int[] sp) {
        if(cp == null || cp.length == 0 || sp == null || sp.length == 0)
            return new int[]{};

        // Calculate profit of each product. selling price - cost price
        int[] profits = new int[cp.length];
        IntStream.range(0, cp.length).forEach(i -> profits[i] = sp[i] - cp[i]);  // O(n)
        return profits;
    }

/*
    Approach#5: Sorting       --  [Overall Time and Space Efficient]
    Time: O(n*log(n)+m)
    Auxiliary Space: O(n)
    Where n is the number of Products
 */
    public int maximizeProfit5(int M, int[] cp, int[] sp) {
        int[] profitsCalculated = calculateProductProfits(cp, sp);    // O(n)
        if(profitsCalculated.length == 0)
            return 0;

        // Sort the profit array in descending order
        Integer[] profits = Arrays.stream(profitsCalculated).boxed().toArray(Integer[]::new);
        Arrays.sort(profits, Collections.reverseOrder());   // O(nLogn)

        // Check for best M profits
        return IntStream.range(0, Math.min(M, profits.length))
                .filter(i -> profits[i] > 0)
                .map(i -> profits[i])
                .sum();
    }

/*
    Approach#4: Dynamic Programming with 1D   --  [Space Efficient]
    Time: O(n*M)
    Space: O(M)
*/
    public int maximizeProfit4(int M, int[] cp, int[] sp) {
        int[] profits = calculateProductProfits(cp, sp);    // O(n)
        if(profits.length == 0)
            return 0;

        int[] dp = dp(profits, M);
        return dp[dp.length-1];
    }

    public int[] dp(int[] profits, int M) {
        int[] dp = new int[M+1];
        for (int profit : profits) {
            for (int j = dp.length - 1; j > 0; j--) {
                if (profit > 0 && dp[j - 1] + profit > dp[j]) {
                    dp[j] = dp[j - 1] + profit;
                }
            }
        }
        return dp;
    }

/*
    Approach#3: Dynamic Programming with 2D   --  [More Efficient]
    Time: O(n*M)
    Space: O(n*M)
*/
    public int maximizeProfit3(int M, int[] cp, int[] sp) {
        int[] profits = calculateProductProfits(cp, sp);    // O(n)
        if(profits.length == 0)
            return 0;

        int[][] dp = dp3(profits, M);
        return dp[dp.length-1][dp[0].length-1];
    }

    public int[][] dp3(int[] profits, int M) {
        int n = profits.length;
        int[][] dp = new int[n+1][M+1];

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                // Option 1: Do not pick the current product
                dp[i][j] = dp[i-1][j];

                // Option 2: Pick the current product if it has a positive profit and the budget allows
                if(profits[i-1] > 0)
                    dp[i][j] = Math.max(dp[i-1][j-1] + profits[i-1], dp[i][j]);
            }
        }
        return dp;
    }

/*
    Approach#2: Backtracking with Memoization   [Efficient]
    Time: O(n*M*S)
    Space: O(n*M*S)
    Where n is the number of Products
    and S is the maximum possible value of currSum.
 */
    public int maximizeProfit2(int M, int[] cp, int[] sp) {
        int[] profits = calculateProductProfits(cp, sp);    // O(n)
        if(profits.length == 0)
            return 0;

        // Initialize memoization table
        Integer[][][] dp = new Integer[profits.length + 1][M + 1][1000];    // Adjust the size of the third dimension as needed
        return backTrackingWithMemoization(profits, M, 0, 0, dp);      // O(n*M*S)
    }

    public int backTrackingWithMemoization(int[] profits, int M, int i, int currSum, Integer[][][] dp) {
        if(i == profits.length || M == 0)
            return currSum;

        if (dp[i][M][currSum] != null)
            return dp[i][M][currSum];

        int notPicked = backtracking(profits, M, i+1, currSum);

        int picked = 0;
        if(profits[i] > 0)
            picked = backtracking(profits, M-1, i+1, currSum+profits[i]);

        return dp[i][M][currSum] = Math.max(notPicked, picked);
    }

/*
    Approach#1: Backtracking        -- [InEfficient]
    Time: O(n) + O(2^n) -> O(n + 2^n)
    Space: O(n) + O(n) -> O(n)
        i- profits array
        ii- recursive depth
    Where n is the number of Products
 */
    public int maximizeProfit1(int M, int[] cp, int[] sp) {
        int[] profits = calculateProductProfits(cp, sp);    // O(n)
        if(profits.length == 0)
            return 0;

        return backtracking(profits, M, 0, 0);      // O(2^n)
    }

    public int backtracking(int[] profits, int M, int i, int currSum) {
        if(i == profits.length || M == 0)
            return currSum;

        int notPicked = backtracking(profits, M, i+1, currSum);

        int picked = 0;
        if(profits[i] > 0)
            picked = backtracking(profits, M-1, i+1, currSum+profits[i]);

        return Math.max(notPicked, picked);
    }

}
