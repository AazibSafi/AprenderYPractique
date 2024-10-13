package com.algorithms.aprenderypractique.Cache;

public class Node<K, V> {
    K key; V value;
    public Node<K, V> prev, next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node() { }
}