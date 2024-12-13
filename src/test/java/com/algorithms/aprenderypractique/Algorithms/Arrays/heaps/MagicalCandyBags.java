package com.algorithms.aprenderypractique.Algorithms.Arrays.heaps;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *      Magical Candy Bags
 *  You have N bags of candy. The ith bag contains arr[i] pieces of candy, and each of the bags is magical!
 *  It takes you 1 minute to eat all of the pieces of candy in a bag (irrespective of how many pieces of candy are inside), and as soon as you finish, the bag mysteriously refills. If there were x pieces of candy in the bag at the beginning of the minute, then after you've finished you'll find that floor(x/2) pieces are now inside.
 *  You have k minutes to eat as much candy as possible. How many pieces of candy can you eat?
 */
public class MagicalCandyBags extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[] {2, 1, 7, 4, 2};
        Assert.assertEquals(14,maxCandies(arr, 3));

        arr = new int[] {19, 78, 76, 72, 48, 8, 24, 74, 29};
        Assert.assertEquals(228,maxCandies(arr, 3));
    }

    public int maxCandies(int[] arr, int mnts) {
        int candies = 0;

        PriorityQueue<Integer> maxHeap = fillMaxHeap(arr);

        while(mnts>0 && maxHeap.size()>0) {
            int bagCandies = maxHeap.poll();
            candies += bagCandies;
            mnts--;
            maxHeap.add(bagCandies/2);
        }

        return candies;
    }

    public PriorityQueue<Integer> fillMaxHeap(int[] arr) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int x : arr) {
            maxHeap.add(x);
        }
        return maxHeap;
    }

}
