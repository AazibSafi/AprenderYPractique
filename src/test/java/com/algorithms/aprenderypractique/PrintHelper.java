package com.algorithms.aprenderypractique;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintHelper {

    public static void printMap(Map<String, String> map) {
        if (map != null) {
            map.forEach((key,value)-> System.out.println(key+" "+value));
        }
    }

    public static void printList(List list) {
        list.forEach(System.out::println);
    }

    public void printArray(int[] arr) {
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            for(int cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public static void printSet(Set set) {
        set.forEach(System.out::println);
    }

}
