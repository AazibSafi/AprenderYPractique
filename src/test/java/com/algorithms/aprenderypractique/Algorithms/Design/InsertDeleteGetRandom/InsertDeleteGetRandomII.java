package com.algorithms.aprenderypractique.Algorithms.Design.InsertDeleteGetRandom;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *      https://leetcode.com/problems/insert-delete-getrandom-o1
 */
public class InsertDeleteGetRandomII extends BaseTest {

/*
    Time: O(1)
    Space: O(1)
    My Easy Solution
 */
    List<Integer> list;
    Random rand;

    public InsertDeleteGetRandomII() {
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        boolean status = !list.contains(val);   // ONly this is the difference from previous problem
        list.add(val);
        return status;
    }

    public boolean remove(int val) {
        if(!list.contains(val))   return false;

        int idxOfElementToDelete = list.indexOf(val);
        int lastElement = list.getLast(); // list.get(list.size()-1);

        list.set(idxOfElementToDelete, lastElement);
        list.removeLast();

        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
