package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LongestPalindrome extends BaseTest {

    @Test
    public void test() {
        System.out.println(longestPalindrome("abzcackllkz"));
    }

    public String longestPalindrome(String str) {
        StringBuilder ptr = new StringBuilder();
        Map<String, Integer> map = new LinkedHashMap<>();
        for(String ch : str.split("")) {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        int totalLength=0;
        for(Map.Entry<String, Integer> charCounts : map.entrySet()) {
            if(charCounts.getValue()%2==0) {
                totalLength += charCounts.getValue();

                for(int i = 0; i<charCounts.getValue();i=i+2) {
                    ptr.insert(0,charCounts.getKey());
                    ptr.insert(ptr.length(),charCounts.getKey());
                }
            }
        }
        System.out.println("Length: "+totalLength);
        return ptr.toString();
    }

    public int countLength(Map.Entry<String, Integer> charCounts) {
        int totalLength = 0,subLength = 0;
        if(charCounts.getValue()%2==0) {
            subLength = charCounts.getValue()/2;
            totalLength += subLength;
        }
        return subLength;
    }
}
