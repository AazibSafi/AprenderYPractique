package com.algorithms.aprenderypractique.algorithm.LRUCache_Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/lru-cache
 *      https://leetcode.com/problems/lru-cache/solutions/45939/laziest-implementation-java-s-linkedhashmap-takes-care-of-everything
 *      https://www.youtube.com/watch?v=7ABFKPK2hD4
 *
 *      Expected Solution in Interview
 *
 *      Todo: https://leetcode.com/problems/lfu-cache
 */
public class LRUCache {
    Map<Integer, Node> cache;
    LRU_Policy policy;           //  Cache Memory Manager
    int capacity;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        policy = new LRU_Policy();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key))     return -1;

        Node node = cache.get(key);
        policy.updateMRU(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node;
        if(cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
        }
        else {
            node = new Node(key, value);
            cache.put(key, node);
        }

        policy.updateMRU(node);

        if(cache.size() > capacity) {
            Node deleteLRU_Node = policy.evictLRU();
            cache.remove(deleteLRU_Node.key);
        }
    }

}

// Memory Management Cache - LRU algorithm
class LRU_Policy {
    Node head, tail;    // MRU, LRU respectively

    LRU_Policy() {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public void add(Node node) {
        Node after = head.next;
        head.next = node;
        node.next = after;
        after.prev = node;
        node.prev = head;
    }

    public void remove(Node node) {
        Node after = node.next;
        Node before = node.prev;
        before.next = after;
        after.prev = before;
    }

    // Most Recent Used Node
    public void updateMRU(Node node) {
        if(node.next!=null && node.prev!=null)  // in case of new Node insertion
            remove(node);
        add(node);
    }

    // Evict Least Recent Used Node
    public Node evictLRU() {
        Node toDel = tail.prev;
        remove(toDel);
        return toDel;
    }
}

class Node {
    int key, value;
    Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
