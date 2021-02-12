package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RemovingBalls extends BaseTest {

    Map<Integer,Integer> cache = new HashMap();

    @Test
    public void solution() {
        cache.put(0,1);
        cache.put(1,1);
        System.out.println(ways(2));
        System.out.println(ways(3));
        System.out.println(ways(4));
        System.out.println(ways(5));
        System.out.println(ways(6));
    }

    int ways(int n) {
        if(cache.containsKey(n)) {
            return cache.get(n);
        }
        int x = ways(n-1) + ways(n-2);
        cache.put(n,x);
        return x;
    }

}
