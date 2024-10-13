package com.algorithms.aprenderypractique.Cache.Policy;

import com.algorithms.aprenderypractique.Cache.Node;

public class MRU_EvictionPolicy<K, V> implements IEvictionPolicy<K, V> {

    public MRU_EvictionPolicy() { }

    @Override
    public Node<K, V> evict() {
        return null;
    }

    @Override   // Get, Insert, Update Operations
    public void notifyRecentTransaction(Node<K, V> node) {

    }

}
