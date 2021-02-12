package com.algorithms.aprenderypractique;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaleedTest2 extends BaseTest {

    @Test
    public void test() {
        int[] A = new int[]{1,2,3,4,5};
        int X = 6;
        System.out.println(solution(X,A));
    }

    public int solution(int X, int[] A) {
        Map<Integer,Integer> map = new HashMap();
        for(int a : A) {
            map.put(a,map.getOrDefault(a,0)+1);
        }

        int countX = map.getOrDefault(X,0);
        return A.length-countX;
    }

    void print(List list) {
        list.stream().forEach(s -> System.out.println(s));
    }

    void print(Map map) {
        map.forEach((X,Y)->{
            System.out.println(X+" "+Y);
        });
    }

}
