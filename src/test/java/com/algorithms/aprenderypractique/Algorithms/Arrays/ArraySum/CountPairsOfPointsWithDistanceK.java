package com.algorithms.aprenderypractique.Algorithms.Arrays.ArraySum;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *      https://leetcode.com/problems/count-pairs-of-points-with-distance-k
 */
public class CountPairsOfPointsWithDistanceK extends BaseTest {

/*  Maths

    (x1 XOR x2) + (y1 XOR y2) = d
    d - (y1 XOR y2) = (x1 XOR x2)

    x = (x1 ^ x2) and y = (y1 ^ y2)
    x2 = (x ^ x1) and y2 = (y ^ y1).

    x2 = (x1 ^ i), y2 = y1 ^ (k-i)
    x2 = (i ^ x1) , y2 = (i ^ y1).
 */

/*
    Time: O(k*n)
    Space: O(n)
*/
    public int countPairs(List<List<Integer>> coordinates, int k) {
        // <X, <Y, Freq> >
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int res=0;

        for(List<Integer> coordinate : coordinates) {
            int x1 = coordinate.get(0), y1 = coordinate.get(1);

            for(int i = 0; i<=k; i++) {
                int x2 = x1 ^ i;
                int y2 = y1 ^ (k-i);

                if(map.containsKey(x2) && map.get(x2).containsKey(y2))
                    res += map.get(x2).get(y2); // Add Freq
            }

            // increase the freq of coordinates by 1
            map.computeIfAbsent(x1, m -> new HashMap<>()).merge(y1, 1, Integer::sum);
        }

        return res;
    }
}


