package com.algorithms.aprenderypractique.Algorithms.Arrays.MaxProfit;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.Arrays;
import java.util.Comparator;

/**
 *       https://www.geeksforgeeks.org/find-maximum-profit-for-products-within-budget
 */
public class FindMaximumProfit2 extends BaseTest {

/*
    Approach#2: Sorting and Binary Search
    Time: O(n + nlogn + m.logn) -> O((n+m).logn)
    Space: O(1) -> Auxiliary Space: (n) if sorting took any additional space
 */
    public static int[] maximumProfitProduct(int[][] products, int[] budgets) {
        if(products == null || products.length == 0)
            return new int[budgets.length];

        int[] output = new int[budgets.length];

        Arrays.sort(products, Comparator.comparingInt(a -> a[0]));  // O(nLogn)

        for(int i=1; i<products.length; i++) {      // O(n)
            products[i][1] = Math.max(products[i-1][1], products[i][1]);
        }

        int idx = 0;
        for(int budget : budgets) {         // O(m)
            if(budget < products[0][0])
                output[idx++] = 0;
            else if(budget >= products[products.length-1][0])
                output[idx++] = products[products.length-1][1];
            else
                output[idx++] = binarySearch(products, budget)[1];      // O(logn)
        }

        return output;
    }

    private static int[] binarySearch(int[][] product, int targetBudget) {
        int left = 0, right = product.length-1;
        int ans = 0;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(targetBudget >= product[mid][0]) {
                ans = left;
                left = mid + 1;
            }
            else
                right = mid - 1;
        }

        return product[ans];
    }

}