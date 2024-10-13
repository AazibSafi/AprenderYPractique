package com.algorithms.aprenderypractique.Cache.Policy;

import java.util.EnumMap;

public class EvictionPolicyFactory<K, V> {

    private final EnumMap<EvictionPolicy, IEvictionPolicy<K, V>> policyPool;

    public EvictionPolicyFactory() {
        policyPool = new EnumMap<>(EvictionPolicy.class);
        policyPool.put(EvictionPolicy.LRU, new LRU_EvictionPolicy<K, V>());
        policyPool.put(EvictionPolicy.MRU, new MRU_EvictionPolicy<K, V>());
    }

    public IEvictionPolicy<K, V> getPolicy(EvictionPolicy policy) {
        return policyPool.get(policy);
    }

}