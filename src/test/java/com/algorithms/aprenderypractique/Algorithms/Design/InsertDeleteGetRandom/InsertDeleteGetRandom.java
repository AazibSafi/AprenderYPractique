package com.algorithms.aprenderypractique.Algorithms.Design.InsertDeleteGetRandom;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.*;

/**
 *      https://leetcode.com/problems/insert-delete-getrandom-o1
 */
public class InsertDeleteGetRandom extends BaseTest {

/*
    Time: O(1)
    Space: O(1)
    My Easy Solution
 */
static class RandomizedSet2 {
        List<Integer> list;
        Random rand;

        public RandomizedSet2() {
            list = new ArrayList<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if(list.contains(val))   return false;
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if(!list.contains(val))   return false;

            int idxOfElementToDelete = list.indexOf(val);
            int lastElement = list.get(list.size()-1);

            list.set(idxOfElementToDelete, lastElement);
            list.removeLast();

            return true;
        }

        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }

/*
    Time: O(1)
    Space: O(1)
    Solution on the internet and LeetCode Editorial
 */
static class RandomizedSet {
        Map<Integer, Integer> dict;
        List<Integer> list;
        Random rand = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {
            dict = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (dict.containsKey(val)) return false;

            dict.put(val, list.size());
            list.add(list.size(), val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (! dict.containsKey(val)) return false;

            // move the last element to the place idx of the element to delete
            int lastElement = list.get(list.size() - 1);
            int idx = dict.get(val);
            list.set(idx, lastElement);
            dict.put(lastElement, idx);
            // delete the last element
            list.remove(list.size() - 1);
            dict.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }

}
