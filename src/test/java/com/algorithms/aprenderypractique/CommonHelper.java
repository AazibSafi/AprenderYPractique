package com.algorithms.aprenderypractique;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        System.out.print("[" + str + "]");
    }

    public static void print2DList(List<List> list) {
        list.forEach( nestedList -> {
            Object str = nestedList.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println("[" + str + "]");
        });
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

    public static String modifyString(String str, String delimeter) {
        return str.chars().mapToObj(c -> String.valueOf( (char) c))
                .collect(Collectors.joining(delimeter,delimeter,delimeter));
    }

    public static Map<String, Integer> sortMap(Map<String, Integer> map) {
        //LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(),x.getValue()));

        return sortedMap;
    }

}
