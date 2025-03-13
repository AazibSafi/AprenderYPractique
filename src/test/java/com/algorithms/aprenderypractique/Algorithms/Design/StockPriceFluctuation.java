package com.algorithms.aprenderypractique.Algorithms.Design;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *      https://leetcode.com/problems/cache-with-time-limit
 *      https://algo.monster/liteproblems/2622
 */
public class StockPriceFluctuation extends BaseTest {

/*
    Time:   O(NlogN) For N calls, it takes O(logN)
    Space:  O(N) -> worst-case, we will add all N records in both the hashmap and sorted map

    Let N is the number of records in the input stream.
*/
    int latestTime;
    // <timestamp, price> Store price of each stock at each timestamp.
    Map<Integer, Integer> timestampPriceMap;

    // <Price, count> Store stock prices in increasing order to get min and max price.
    TreeMap<Integer, Integer> priceFrequency;

    public StockPriceFluctuation() {
        latestTime = 0;
        timestampPriceMap = new HashMap<>();
        priceFrequency = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        latestTime = Math.max(latestTime, timestamp);

        // If same timestamp occurs again, previous price was wrong.
        if(timestampPriceMap.containsKey(timestamp)) {
            // Remove previous price.
            int oldPrice = timestampPriceMap.get(timestamp);
            priceFrequency.merge(oldPrice, -1, Integer::sum);

            // Remove the entry from the map.
            if(priceFrequency.get(oldPrice) <= 0)
                priceFrequency.remove(oldPrice);
        }

        // Add latest price for timestamp.
        timestampPriceMap.put(timestamp, price);
        priceFrequency.merge(price, 1, Integer::sum);
    }

    public int current() {
        return timestampPriceMap.get(latestTime);
    }

    public int maximum() {
        return priceFrequency.lastKey();
    }

    public int minimum() {
        return priceFrequency.firstKey();
    }
}
