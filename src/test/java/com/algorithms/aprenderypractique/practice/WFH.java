package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class WFH extends BaseTest {

    @Test
    public void skdhajld() {
        int arr[] = new int[]{34,31,34,77,82};
        System.out.println(mostPopularNumber(arr,arr.length));
    }

    public int mostPopularNumber(int arr[], int size) {
        Map<Integer,Integer> map = new LinkedHashMap<>();

        for(int i=0;i<size;i++) {
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
            //map.put(arr[i],map.get(arr[i]) == null?1:map.get(arr[i])+1);
        }

        int maxvalue = map.get(arr[0]);
        int key = arr[0];
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxvalue) {
                maxvalue = entry.getValue();
                key = entry.getKey();
            }
            else if (entry.getValue() == maxvalue) {
                if (entry.getKey() < key) {
                    maxvalue = entry.getValue();
                    key = entry.getKey();
                }
            }
        }
        return key;
    }

}
