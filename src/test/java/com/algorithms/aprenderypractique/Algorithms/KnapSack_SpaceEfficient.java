package com.algorithms.aprenderypractique.Algorithms;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  0/1 Knapsack problem | Dynamic Programming
 *
 *  https://www.youtube.com/watch?v=8LusJS5-AGo
 *  https://www.youtube.com/watch?v=cJ21moQpofY
 *
 *  For All other approaches of implementation
 *  https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10
 */
public class KnapSack_SpaceEfficient extends BaseTest {

    @Test
    public void testFindMaxValue() {
        int[] weight = new int[]{1, 3, 4, 5};
        int[] value = new int[]{1, 4, 5, 7};
        Assert.assertEquals(9, findMaxValue(7,weight,value));

        weight = new int[]{4, 3, 2, 3, 1};
        value = new int[]{5, 2, 3, 4, 2};
        Assert.assertEquals(10, findMaxValue(7,weight,value));

        weight = new int[]{3, 2, 4};
        value = new int[]{6, 8, 7};
        Assert.assertEquals(15, findMaxValue(8,weight,value));

        weight = new int[]{6};
        value = new int[]{10};
        Assert.assertEquals(0, findMaxValue(4,weight,value));
    }

/*
     Dynamic programming approach but with optimized space complexity
 *  w -> weight --> same length as value array
 *  c -> capacity
 *  Time: O(w*c)
 *  Space: O(w) -- Only 1-D array is used
 */
    public int findMaxValue(int totalCapacity, int[] weight, int[] value) {
        int[] dp = new int[totalCapacity+1];

        for(int item=0; item<weight.length; item++) {
            for(int capacity=dp.length-1; capacity>=0; capacity--) {

                if(capacity >= weight[item] &&
                        dp[capacity-weight[item]] + value[item] > dp[capacity]) {

                    dp[capacity] = dp[capacity-weight[item]] + value[item];
                }

            }
        }

        return dp[dp.length-1];
    }

    @Test
    public void testFindMaxValueItems() {
        int[] weight = new int[]{1, 3, 4, 5};
        int[] value = new int[]{1, 4, 5, 7};
        printList( findMaxValueItems(7,weight,value) ); // [{weight: 4, value: 5},{weight: 3, value: 4}]

        weight = new int[]{4, 3, 2, 3, 1};
        value = new int[]{5, 2, 3, 4, 2};
        printList( findMaxValueItems(7,weight,value) ); // [{weight: 1, value: 2},{weight: 2, value: 3},{weight: 4, value: 5}]
    }

/*
 *  w -> weight --> same length as value array
 *  c -> capacity
 *  Time: O(w*c)
 *  Space: O(w*c)
 *      The use of 2-D array of size  w*c
 *
 */
    public List<Item> findMaxValueItems(int totalCapacity, int[] weight, int[] value) {
        int[] dp = new int[totalCapacity+1];        // To store the length of selected elements
        int[] dpItems = new int[totalCapacity+1];   // To store the selected elements

        Arrays.fill(dpItems, -1);

        for(int item=0; item<weight.length; item++) {
            for(int capacity=dp.length-1; capacity>=0; capacity--) {
                if(capacity >= weight[item] &&
                        dp[capacity-weight[item]] + value[item] > dp[capacity]) {

                    dp[capacity] = dp[capacity-weight[item]] + value[item];
                    dpItems[capacity] = item;

                }
            }
        }

        return extractSelectedItems(dpItems,weight,value);
    }

//  Note: 1-D array solution Not working properly for selected Items.
    public List<Item> extractSelectedItems(int[] dpItems, int[] weight, int[] value) {
        List<Item> selectedItems = new ArrayList<>();

        int n = dpItems.length-1;

        while(n >=0 && dpItems[n] != -1) {
            selectedItems.add(new Item(weight[dpItems[n]],value[dpItems[n]]));
            n = n - weight[dpItems[n]];
        }

        return selectedItems;
    }

    static class Item {
        int weight, value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static void printList(List<Item> list) {
        Object str = list.stream().map(item -> "{weight: " + item.weight + ", value: " + item.value + "}").collect(Collectors.joining(","));
        System.out.println("[" + str + "]");
    }

}
