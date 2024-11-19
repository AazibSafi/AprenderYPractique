package com.algorithms.aprenderypractique;

import com.algorithms.aprenderypractique.algorithm.datastructure.LinkedList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonHelper {

    public static void printMap(Map<?,?> map) {
        if (map != null) {
            map.forEach((key,value)-> System.out.println("{" + key+" , "+value + "}"));
        }
    }

    public static void printList(List<?> list) {
        Object str = list.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.print("[" + str + "]");
    }

    public static void print2DList(List<List<?>> list) {
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

    public static void printSet(Set<?> set) {
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
            table.put(c, table.getOrDefault(c,0)+1);
        }
        return table;
    }

    public static Map<Integer, Long> fillTableWithOccurrences(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static int[] fillArrayFrequencies(String T) {
        int[] alphabets = new int[26];
        for(char c : T.toCharArray())       alphabets[c - 'a']++;
        return alphabets;
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

    public static boolean isEquals(Collection<?> a, Collection<?> b) {
        return a.size() == b.size() && a.containsAll(b);
    }

    public static int combineArrayElements(int[] arr) {
        String result = IntStream.of(arr).boxed().map(String::valueOf).collect(Collectors.joining());
        return Integer.parseInt(result);
    }

    public static LinkedList createLinkedList(int[] arr) {
        LinkedList head = new LinkedList(-1);
        LinkedList node = head;

        for(int x : arr) {
            node.next = new LinkedList(x);
            node = node.next;
        }

        return head.next;
    }

    public static boolean isEqual(LinkedList head1, LinkedList head2) {
        while(head1 != null && head2 != null) {
            if(head1.val != head2.val)
                return false;

            head1 = head1.next;
            head2 = head2.next;
        }

        return head1 == null && head2 == null;
    }

}
