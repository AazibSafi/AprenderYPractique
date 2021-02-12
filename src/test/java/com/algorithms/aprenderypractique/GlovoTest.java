package com.algorithms.aprenderypractique;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlovoTest extends BaseTest {

    @Test
    public void solution() {
//        System.out.println(findFirstDuplicate("ABCA"));
//        System.out.println(findFirstDuplicate("BCABA"));
        System.out.println(findFirstDuplicate("glovol"));
        System.out.println(findFirstDuplicate("ABC"));
        System.out.println(findOccurences(new int[]{1,3,2,3,4},3));

    }

    public int findOccurences(int[] arr, int x) {
        int count = 0;
        for(int item : arr) {
            if(item == x) {
                count++;
            }
        }
        return count;
    }

    public Character findFirstDuplicate(String str) {
        Character res = null;
        Map<Character,Integer> map = new LinkedHashMap<>();
        for(int i=0;i<str.length();i++) {
            map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+1);
        }

        for(Map.Entry<Character,Integer> element : map.entrySet()) {
            if(element.getValue() > 1) {
                res = element.getKey();
                break;
            }
        }
        return res;
    }

}
