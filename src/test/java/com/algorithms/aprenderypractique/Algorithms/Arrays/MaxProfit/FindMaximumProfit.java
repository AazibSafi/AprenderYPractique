package com.algorithms.aprenderypractique.Algorithms.Arrays.MaxProfit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 *       https://www.geeksforgeeks.org/find-maximum-profit-for-products-within-budget
 */
public class FindMaximumProfit {

    @ParameterizedTest
    @MethodSource("testCases")
    public void testMaximumProfitProduct(int[][] products, int[] budgets, int[] expected) {
        Assertions.assertArrayEquals(expected, maximumProfitProduct(products, budgets));
        Assertions.assertArrayEquals(expected, FindMaximumProfit2.maximumProfitProduct(products, budgets));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        new int[][]{{2, 3}, {3, 6}, {4, 1}, {7, 12}, {4, 7}},
                        new int[]{2, 3, 4, 6, 7, 8},
                        new int[]{3, 6, 7, 7, 12, 12}
                ),
                Arguments.of(
                        new int[][]{{40, 22}, {20, 6}},
                        new int[]{10, 5},
                        new int[]{0, 0}
                ),
                Arguments.of(
                        new int[][]{{10, 20}, {15, 25}, {20, 30}},
                        new int[]{5, 8, 9},
                        new int[]{0, 0, 0}
                ),
                Arguments.of(
                        new int[][]{{1, 10}, {2, 15}, {3, 20}, {4, 25}},
                        new int[]{5, 6, 10},
                        new int[]{25, 25, 25}
                ),
                Arguments.of(
                        new int[][]{{43, 10}, {3, 20}, {3, 15}, {5, 25}},
                        new int[]{3, 4, 5},
                        new int[]{20, 20, 25}
                ),
                Arguments.of(
                        new int[][]{},
                        new int[]{1, 2, 3},
                        new int[]{0, 0, 0}
                ),
                Arguments.of(
                        new int[][]{{5, 50}},
                        new int[]{4, 5, 6},
                        new int[]{0, 50, 50}
                )
        );
    }

/*
    Approach#1: Linear Scan
    Time: O(m . n)
    Space: O(1)
 */
    public int[] maximumProfitProduct(int[][] products, int[] budgets) {
        if(products == null || products.length == 0)
            return new int[budgets.length];

        int[] output = new int[budgets.length];
        int idx = 0;

        for(int budget : budgets) {     // O(m)
            int max = 0;
            for(int[] product : products) {     // O(n)
                if(product[0] <= budget && product[1] > max) {
                    max = product[1];
                }
            }
            output[idx++] = max;
        }
        return output;
    }

}
