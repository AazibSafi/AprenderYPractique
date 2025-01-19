package com.algorithms.aprenderypractique.Algorithms.Design.HitCounter;

import java.util.ArrayList;
import java.util.List;

/**
 *  https://leetcode.com/problems/design-hit-counter
    Approach#1: Linear Scan
    Time:
        hit: O(1)
        getHits: O(n)   ->  Iterates full array
    Space:
        O(n)
*/
class HitCounter_LinearScan {
    private final List<Integer> data;
    private final int EXPIRY = 300;

    public HitCounter_LinearScan() {
        data = new ArrayList<>();
    }

    public void hit(int timestamp) {
        data.add(timestamp);
    }

    public int getHits(int timestamp) {
        return (int) data.stream().filter(prevTime -> timestamp - prevTime < EXPIRY).count();
    }
}
