package com.algorithms.aprenderypractique.Cache;

import com.algorithms.aprenderypractique.Cache.Policy.EvictionPolicy;
import com.algorithms.aprenderypractique.Cache.Policy.EvictionPolicyFactory;
import com.algorithms.aprenderypractique.Cache.Policy.IEvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> {

    IEvictionPolicy<K, V> policy;
    Map<K, Node<K, V>> cache;
    int capacity;

    public Cache(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;
        this.policy = new EvictionPolicyFactory<K, V>().getPolicy(EvictionPolicy.LRU);
    }

    public Cache(int capacity, EvictionPolicy policyType) {
        cache = new HashMap<>();
        this.capacity = capacity;
        this.policy = new EvictionPolicyFactory<K, V>().getPolicy(policyType);
    }

    public V get(K key) {
        if(!cache.containsKey(key))     return null;

        Node<K, V> node = cache.get(key);
        policy.notifyRecentTransaction(node);
        return node.value;
    }

    public void put(K key, V value) {
//        Node<K, V> node;
//        if(cache.containsKey(key)) {
//            node = cache.get(key);
//            node.value = value;
//        }
//        else {
//            node = new Node<>(key, value);
//            cache.put(key, node);
//        }

        Node<K, V> node = cache.getOrDefault(key, new Node<>());
        node.key = key;
        node.value = value;
        cache.put(key, node);

        policy.notifyRecentTransaction(node);

        if(cache.size() > capacity) {
            Node<K, V> toDel = policy.evict();
            cache.remove(toDel.key);
        }
    }

}
