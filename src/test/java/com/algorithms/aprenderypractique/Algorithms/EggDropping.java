package com.algorithms.aprenderypractique.Algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  https://www.youtube.com/watch?v=o_AJ3VWQMzA
 *  https://github.com/gkcs/Competitive-Programming/blob/master/src/main/java/main/java/videos/EggSolver.java
 *
 *  Todo:   https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors
 *          https://leetcode.com/problems/super-egg-drop
 */
public class EggDropping {

    @Test
    public void test() {
        for(int i=0; i<100; i++) {
            if(Character.isEmoji(i))
                System.out.println(i+" is Emoji");
        }

        var sb = new StringBuilder();
        sb.repeat("One ", 5);
        System.out.println(sb);

        var map = new HashMap<>();
        map.put(1,1);
        map.entrySet();

        HashMap.newHashMap(100);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Middle");
        list.add("Last");

        System.out.println("First:: " + list.getFirst());
        System.out.println("Last:: " + list.getLast());
    }

    @Test
    public void processInputNew() {
        String input = "No";
        String output = null;
        switch(input) {
            case null -> output = "Oops, null";
            case String s when "Yes".equalsIgnoreCase(s) -> output = "It's Yes";
            case String s when "No".equalsIgnoreCase(s) -> output = "It's No";
            case String s -> output = "Try Again";
        }
        System.out.println("output: " + output);
    }

}
