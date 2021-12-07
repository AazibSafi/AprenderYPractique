package com.algorithms.aprenderypractique.algorithm.datastructure;

public class LinkedList {

    public int val;
    public LinkedList next;

    public LinkedList(int x) {
        this.val = x;
    }

    public LinkedList(int x, LinkedList next) {
        this.val = x;
        this.next = next;
    }

}
