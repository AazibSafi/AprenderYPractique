package com.algorithms.aprenderypractique.Cache.Policy;

public enum EvictionPolicy {
    LRU("Least Recently Used"),
    MRU("Most Recently Used"),
    FIFO("First In First Out");

    public final String desc;

    EvictionPolicy(String desc) {
        this.desc = desc;
    }

}
