package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;

/**
 *  https://leetcode.com/problems/count-and-say
 */
public class CountAndSay extends BaseTest {

    public String countAndSay(int n) {
        if(n == 1)  return "1";
        return RLE(countAndSay(n - 1));
    }

    String RLE(String x) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<x.length(); i++) {
            char c = x.charAt(i);
            int count = 1;
            while(i+1<x.length() && x.charAt(i) == x.charAt(i+1)) {
                count++;
                i++;
            }
            sb.append(count + "" + c);
        }
        return sb.toString();
    }

}
