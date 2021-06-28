package com.algorithms.aprenderypractique;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonHelper {

    public static void printMap(Map map) {
        if (map != null) {
            map.forEach((key,value)-> System.out.println(key+" "+value));
        }
    }

    public static void printList(List list) {
        list.forEach(System.out::println);
    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            for(int cell=0;cell<row.length; cell++) {
                System.out.print(cell);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void printSet(Set set) {
        set.forEach(System.out::println);
    }

//    ignore all characters other than alphabets and space
    public static String formatOnlyAlphabets(String str) {
        return str.replaceAll("[^a-zA-Z\\s]","");
    }

    public static Map<Character,Integer> fillTableWithOccurrences(String T) {
        Map<Character,Integer> table = new HashMap<>();
        for(Character c : T.toCharArray()) {
            table.put(c,table.getOrDefault(c,0)+1);
        }
        return table;
    }

    public static Map<String,Integer> fillTableWithOccurrences(String[] sentences) {
        Map<String,Integer> table = new HashMap<>();
        for(String word : sentences) {
            table.put(word,table.getOrDefault(word,0)+1);
        }
        return table;
    }

}
