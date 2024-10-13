package com.algorithms.aprenderypractique.Cache.Policy;

import com.algorithms.aprenderypractique.Cache.Node;

public interface IEvictionPolicy<K, V> {

    Node<K, V> evict();
    void notifyRecentTransaction(Node<K, V> node);

}
