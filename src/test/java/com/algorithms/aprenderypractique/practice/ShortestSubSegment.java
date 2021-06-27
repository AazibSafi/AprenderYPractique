package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * Problem: https://www.hackerrank.com/contests/amazon/challenges/shortest-sub-segment/problem
 * Algorithm Reference: MinimumWindowSubstring.java
 */
public class ShortestSubSegment extends BaseTest {

    @Test
    public void test() {
        String str= "This is a test. This is a programming test. This is a programming test in any language.";
        String K = "this a test programming";
        String result = shortestSubSegment(str,K);
        System.out.println(result);
        Assert.assertEquals("a programming test This",result);
    }

    public String shortestSubSegment(String str, String K) {

        str = CommonHelper.formatOnlyAlphabets(str);

        String[] sentenceWords = str.split(" ");
        String[] kWords = K.split(" ");

        Map<String,Integer> table = fillTableWithOccurrences(kWords);

        int leftPointer=0, rightPointer=0;
        int[] minWindowResult = new int[]{0,-1}; // {startIndex, length}
        int count=0;

        while(rightPointer<sentenceWords.length) {

            while(count!=kWords.length && rightPointer<sentenceWords.length) {
                String word = sentenceWords[rightPointer++];
                if(table.containsKey(word)) {
                    table.put(word,table.get(word)-1);

                    if(table.get(word)>=0) {
                        count++;
                    }
                }
            }

            saveMinResultIfAny(minWindowResult,leftPointer,rightPointer-1);

            while(count==kWords.length && leftPointer<rightPointer-1) {

                saveMinResultIfAny(minWindowResult,leftPointer,rightPointer-1);

                String word = sentenceWords[leftPointer];

                if(table.containsKey(word)) {
                    table.put(word,table.get(word)+1);

                    if(table.get(word)>0) {
                        count--;
                    }
                }
                leftPointer++;
            }

        }

        StringBuilder builder = new StringBuilder();
        for(int i=minWindowResult[0];i<minWindowResult[0]+minWindowResult[1];i++) {
            builder.append(sentenceWords[i]);

            if(i!=minWindowResult[0]+minWindowResult[1]-1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    private void saveMinResultIfAny(int[] minWindowResult, int leftPointer, int rightPointer) {
        int newWindowLength = rightPointer - leftPointer +1;
        if(minWindowResult[1] == -1 ||
                newWindowLength < minWindowResult[1]) {
            minWindowResult[1] = newWindowLength;
            minWindowResult[0] = leftPointer;
        }
    }

    public Map<String,Integer> fillTableWithOccurrences(String[] sentences) {
        Map<String,Integer> table = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);   // Keys will be case insensitive

        for(String word : sentences) {
            table.put(word,table.getOrDefault(word,0)+1);
        }
        return table;
    }


}
