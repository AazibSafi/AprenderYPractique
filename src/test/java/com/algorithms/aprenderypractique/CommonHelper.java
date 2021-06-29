package com.algorithms.aprenderypractique;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonHelper {

    public static void printMap(Map map) {
        if (map != null) {
            map.forEach((key,value)-> System.out.println("{" + key+" , "+value + "}"));
        }
    }

    public static void printList(List list) {
        Object str = list.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.print("\n[" + str + "]");
    }

    public static void printArray(int[] arr) {
        Object str = Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(","));
        System.out.print("\n[" + str + "]");
    }

    public static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            printArray(row);
            System.out.println();
        }
    }

    public static void printSet(Set set) {
        Object str = set.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.print("\n[" + str + "]");
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

}
