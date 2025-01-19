package com.algorithms.aprenderypractique.Algorithms.Arrays.Heaps;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 *      https://leetcode.com/problems/sort-integers-by-the-power-value
 *      The Collatz Conjecture problem: 3x + 1
 */
public class SortIntegersByThePowerValue extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(13, getKth(12, 15, 2));
        Assert.assertEquals(7, getKth(7, 11, 4));
    }

/*
    Time: O((Hi−Lo)*Log(K))
    Space: O((Hi−Lo) + K)
*/
    Map<Integer, Integer> cache;
    public int getKth(int lo, int hi, int k) {
        cache = new HashMap<>();

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> a[1]==b[1]? b[0]-a[0] : b[1]-a[1]);

        IntStream.rangeClosed(lo, hi).forEach(x -> {    // O(Hi−Lo)
            maxHeap.add(new int[]{x, power(x)});
            if(maxHeap.size() > k)
                maxHeap.remove();       // O(LogK)
        });

        return maxHeap.remove()[0];
    }

    private int power(int n) {
        if(n == 1) return 0;

        // Retrieve from cache
        if(cache.containsKey(n)) return cache.get(n);

        int power = 1 + ((n % 2 == 0) ?  power(n / 2) : power((3 * n) + 1));

        // Save in cache
        cache.put(n, power);

        return power;
    }

}
