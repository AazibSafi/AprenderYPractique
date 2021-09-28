package com.algorithms.aprenderypractique.Toptal.attemp2;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task2 extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[] {2,1,1,3,2,1,1,3};
        Assert.assertEquals(3,solution(arr));

        arr = new int[] {7,5,2,7,2,7,4,7};
        Assert.assertEquals(6,solution(arr));
    }

    public int solution(int[] A) {
        int[] locations = getDistinctElements(A);
        String subArray = findMinimumWindowSubstring(convertIntToString(A),convertIntToString(locations));
        return subArray.length();
    }

    Map<Character,Integer> fillTableWithOccurrences(String T) {
        Map<Character,Integer> table = new HashMap<>();
        for(Character c : T.toCharArray()) {
            table.put(c,table.getOrDefault(c,0)+1);
        }
        return table;
    }

    public String findMinimumWindowSubstring(String str, String T) {

        Map<Character,Integer> table = fillTableWithOccurrences(T);

        int count = 0;
        int[] minLengthResult = new int[]{-1,0};
        int rightPointer=0, leftPointer=0;

        while(rightPointer<str.length()) {

            while(count != T.length() && rightPointer<str.length()) {
                char c = str.charAt(rightPointer++);

                if(table.containsKey(c)) {
                    table.put(c,table.get(c) - 1);

                    if(table.get(c) >=0) {
                        count++;
                    }
                }
            }

            int currWindowEnd = rightPointer-1;

            saveMinLengthResult(minLengthResult,leftPointer,currWindowEnd);

            while(count==T.length() && leftPointer<currWindowEnd) {

                saveMinLengthResult(minLengthResult,leftPointer,currWindowEnd);

                char c = str.charAt(leftPointer++);

                if(table.containsKey(c)) {
                    table.put(c,table.get(c) + 1);

                    if(table.get(c) > 0) {
                        count--;
                    }
                }
            }

        }
        return str.substring(minLengthResult[1], minLengthResult[1] + minLengthResult[0]);
    }

    void saveMinLengthResult(int[] minLengthResult, int start, int end) {
        int newLength = end-start+1;
        if(minLengthResult[0] == -1 || newLength < minLengthResult[0]) {
            minLengthResult[0] = newLength;
            minLengthResult[1] = start;
        }
    }

    int[] getDistinctElements(int[] A) {
        Set<Integer> set = new HashSet<>();
        for(int x : A)      set.add(x);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    String convertIntToString(int[] arr) {
        StringBuilder strArray = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            strArray.append(arr[i]);
        }
        return strArray.toString();
    }

}
