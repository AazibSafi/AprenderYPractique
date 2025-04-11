package com.algorithms.aprenderypractique.Algorithms;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
/*
    Approach#2: Floyd's Cycle-Finding Algorithm
    Time: O(logn)
    Sapce: O(1)
*/
    public boolean isHappy(int n) {
        int slowRunner = n, fastRunner = getNext(n);
        while(fastRunner != 1 && fastRunner != slowRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

/*
    Approach#1: HashSet
    Time: O(logn)
    Sapce: O(logn)
*/
    public boolean isHappy1(int n) {
        Set<Integer> seen = new HashSet<>();
        while(n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    int getNext(int n) {
        int totalSum = 0;
        while(n != 0) {
            int d = n%10;
            totalSum += d*d;
            n /= 10;
        }
        return totalSum;
    }

}
