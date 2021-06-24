package com.algorithms.aprenderypractique;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        int n = arr.length;
        for (int[] row : arr) {
            for (int j=0; j<n; j++) {
                System.out.print(row[j]);
            }
            System.out.println();
        }
    }

}
