package com.algorithms.aprenderypractique.Cache.Policy;

import com.algorithms.aprenderypractique.Cache.Node;

public class LRU_EvictionPolicy<K, V> implements IEvictionPolicy<K, V> {
    Node<K, V> head, tail;    // MRU, LRU respectively

    public LRU_EvictionPolicy() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public Node<K, V> evict() {
        Node<K, V> toDel = tail.prev;
        remove(toDel);
        return toDel;
    }

    @Override   // Get, Insert, Update Operations
    public void notifyRecentTransaction(Node<K, V> node) {
        if(node.next!=null && node.prev!=null)  // in case of new Node insertion
            remove(node);
        add(node);
    }

    public void add(Node<K, V> node) {
        Node<K, V> after = head.next;
        head.next = node;
        node.next = after;
        after.prev = node;
        node.prev = head;
    }

    public void remove(Node<K, V> node) {
        Node<K, V> after = node.next;
        Node<K, V> before = node.prev;
        before.next = after;
        after.prev = before;
    }

}
