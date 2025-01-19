package com.algorithms.aprenderypractique.Algorithms.Design.HitCounter;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  https://leetcode.com/problems/design-hit-counter
    Approach#2: Using Queue and Early Return Policy to speed up
    Time:
        hit: O(1)
        getHits: O(n)   -> iterates array upto all expired data -> worst case is O(n)
    Space:
        O(n)
*/
class HitCounter_Queue {
    private final Queue<Integer> data;
    private final int EXPIRY = 300;

    public HitCounter_Queue() {
        data = new LinkedList<>();
    }

    public void hit(int timestamp) {
        data.add(timestamp);
    }

    public int getHits(int timestamp) {
        while(!data.isEmpty() && timestamp - data.peek() >= EXPIRY) {
            data.remove();      // Poll Out of Window timestamps
        }
        return data.size();
    }
}
