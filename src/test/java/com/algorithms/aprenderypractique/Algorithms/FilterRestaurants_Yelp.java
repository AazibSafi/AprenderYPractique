package com.algorithms.aprenderypractique.Algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  https://leetcode.com/problems/filter-restaurants-by-vegan-friendly-price-and-distance
 */
public class FilterRestaurants_Yelp {

    // Time: O(N)
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        return Arrays.stream(restaurants)
                .filter(res -> res[2] >= veganFriendly)
                .filter(res -> res[3] <= maxPrice)
                .filter(res -> res[4] <= maxDistance)
                .sorted((a,b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1])
                .map(res -> res[0])
                .collect(Collectors.toList());
    }

}
