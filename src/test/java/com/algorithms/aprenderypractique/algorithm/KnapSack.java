package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  0/1 Knapsack problem | Dynamic Programming
 *
 *  https://www.youtube.com/watch?v=8LusJS5-AGo
 *  https://www.youtube.com/watch?v=cJ21moQpofY
 *
 *  For All other approaches of implementation
 *  https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 */
public class KnapSack extends BaseTest {

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
    Dynamic Programming - Bottom Up Manner
 *  w -> weight --> same length as value array
 *  c -> capacity
 *  Time: O(w*c)
 *  Space: O(w*c)
 *      The use of 2-D array of size  w*c
 */
    public int findMaxValue(int totalCapacity, int[] weight, int[] value) {
        int[][] T = new int[weight.length][totalCapacity+1];

        for(int itemInd=0; itemInd<T.length; itemInd++) {
            for(int capacity=1; capacity<T[0].length; capacity++) {     // first column of DP will always be 0, When the capacity is 0
                if(capacity >= weight[itemInd]) {
                    if(itemInd == 0) {
                        T[itemInd][capacity] = value[itemInd];
                    }
                    else {
                        int upperValue = T[itemInd - 1][capacity];
                        int remainingAddedValue = value[itemInd] + T[itemInd - 1][capacity - weight[itemInd]];
                        T[itemInd][capacity] = Math.max(upperValue,remainingAddedValue);
                    }
                }
            }
        }
        return T[T.length-1][T[0].length-1];    // Last element of DP is the max value
    }

    @Test
    public void testFindMaxValueItems() {
        int[] weight = new int[]{1, 3, 4, 5};
        int[] value = new int[]{1, 4, 5, 7};
        printList( findMaxValueItems(7,weight,value) ); // [{weight: 4, value: 5},{weight: 3, value: 4}]

        weight = new int[]{4, 3, 2, 3, 1};
        value = new int[]{5, 2, 3, 4, 2};
        printList( findMaxValueItems(7,weight,value) ); // [{weight: 1, value: 2},{weight: 2, value: 3},{weight: 4, value: 5}]

        weight = new int[]{3, 2, 4};
        value = new int[]{6, 8, 7};
        printList( findMaxValueItems(8,weight,value) ); // [{weight: 4, value: 7},{weight: 2, value: 8}]

        weight = new int[]{6};
        value = new int[]{3};
        printList( findMaxValueItems(4,weight,value) );  //   []
    }

    public List<Item> findMaxValueItems(int totalCapacity, int[] weight, int[] value) {
        int[][] T = new int[weight.length][totalCapacity+1];

        for(int itemInd=0; itemInd<T.length; itemInd++) {
            for(int capacity=1; capacity<T[0].length; capacity++) {     // first column of DP will always be 0, When the capacity is 0
                if(capacity >= weight[itemInd]) {
                    if(itemInd == 0) {
                        T[itemInd][capacity] = value[itemInd];
                    }
                    else {
                        int upperValue = T[itemInd - 1][capacity];
                        int remainingAddedValue = value[itemInd] + T[itemInd - 1][capacity - weight[itemInd]];
                        T[itemInd][capacity] = Math.max(upperValue,remainingAddedValue);
                    }
                }
            }
        }

//        -------   Till here same as previous method

        return getSelectedItems(T,weight,value);
    }

    public List<Item> getSelectedItems(int[][] T, int[] weight, int[] value) {
        List<Item> selectedItems = new ArrayList<>();
        //int i = T.length-1, j= T[0].length-1;

        for(int i = T.length-1, j= T[0].length-1; i >=0 && j >= 0 && T[i][j] != 0; i--) {
            if(i-1 < 0) {
                selectedItems.add(new Item(weight[i], value[i]));
                break;
            }
            else if(T[i][j] != T[i-1][j]) {
                selectedItems.add(new Item(weight[i], value[i]));
                j = j-weight[i];
            }
        }
        return selectedItems;
    }

     class Item {
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
