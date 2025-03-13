package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MaxNoOfEvents;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *      https://leetcode.com/problems/maximum-earnings-from-taxi
 *
 *  @see TwoBestNonOverlappingEvents
 *  @see MaximumNumberOfEventsThatCanBeAttendedII
 */
public class MaximumEarningsFromTaxi extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(7, maxTaxiEarnings(5, new int[][]{{2,5,4},{1,5,1}}));
        Assert.assertEquals(9, maxTaxiEarnings(9, new int[][]{{2,5,4},{1,5,1},{1,4,6}}));
        Assert.assertEquals(20, maxTaxiEarnings(20, new int[][]{{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}}));
    }

/*
    Approach: Top-down Dynamic Programming

        Sorting: O(nlogn)
        Binary Search: O(logn)
        Memoization Ensure iterate each ride once: (n)
    Overall Time: O(n * logn)

        Memoization: O(n)
        Recursive stack: O(n)
    Overall Space: O(n)
*/
    public long maxTaxiEarnings(int n, int[][] rides) {
        // Array Sort by start
        Arrays.sort(rides, (a,b) -> a[0] == b[0] ? a[1] - b[1] :  a[0] - b[0]);

        // initialize memo with rides index
        long[] memo = new long[rides.length];
        Arrays.fill(memo, -1L);

        return solve(rides, 0, memo);
    }

    public long solve(int[][] rides, int i, long[] memo) {
        // if the all rides are completed
        if(i >= rides.length)   return 0;

        // The current point with same rides has already been traversed
        if(memo[i] != -1)   return memo[i];

        int start = rides[i][0], end = rides[i][1], tip = rides[i][2];

        int nextRide = findNextRidePossible(rides, i+1, end);
        long pickup = (end - start + tip) + solve(rides, nextRide, memo);
        long skipPickup = solve(rides, i+1, memo);

        return memo[i] = Math.max(pickup, skipPickup);
    }

    int findNextRidePossible(int[][] rides, int low, int tripEndPoint) {
        int high = rides.length-1, nextRide = rides.length;
        while(low <= high) {
            int mid = low + (high - low)/2;

            if(rides[mid][0] >= tripEndPoint) {
                nextRide = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return nextRide;
    }

}
