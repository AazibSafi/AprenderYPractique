package com.algorithms.aprenderypractique.Algorithms.Design.HitCounter;

import java.util.ArrayList;
import java.util.List;

/**
 *      https://leetcode.com/problems/design-hit-counter
 *      https://www.youtube.com/watch?v=MKihMUdG3O8&ab_channel=CrackingFAANG
    Approach#3: Binary Search
    Time:
        hit: O(1)
        getHits: O(logn)   -> Binary search on the data set to find the start index
    Space:
        O(n)

    Note: This approach looks efficient but when the data grow it will take so much space
        Since Binary search is not removing invalid data unlike HitCounter_Queue
*/
public class HitCounter_BinarySearch {
    private final List<Integer> data;
    private final int EXPIRY = 300;

    public HitCounter_BinarySearch() {
        data = new ArrayList<>();
    }

    public void hit(int timestamp) {
        data.add(timestamp);
    }

    public int getHits(int timestamp) {
        int startRange = timestamp - EXPIRY;
        int startRangeIdx = upperBound_binarySearch(startRange);
        return data.size() - startRangeIdx;
    }

    private int upperBound_binarySearch(int target) {
        int left = 0, right = data.size()-1;
        while(left <= right) {
            int mid = (left+right)/2;

            if(target >= data.get(mid))
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}

