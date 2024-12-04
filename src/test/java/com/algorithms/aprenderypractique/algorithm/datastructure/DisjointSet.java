package com.algorithms.aprenderypractique.algorithm.datastructure;

/**
 *  Disjoint-set/Union-find Forest Implementation
 *  Disjoint Set Union (DSU)
 *
 *  Todo: Look for Optimization of DSU
 *          https://www.naukri.com/code360/library/disjoint-set-union-find-algorithm
 */
public class DisjointSet {
    int[] parent;

    public DisjointSet(int size) {
        parent = new int[size];
        for(int i=0; i<size; i++)   // Make Set
            parent[i] = i;
    }

    /**
     *  Merge the two components that x, y belongs to respectively.
     *      Time: O(log N)
     *      Space: O(n)
     *      where n is the number of nodes
     */
    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if(parentX != parentY)
            parent[x] = y;
    }

    /**
     *  Return the component id that the element x belongs to
     *      Time: O(log N)
     *      Space: O(n)
     *      where n is the number of nodes
     */
    public int find(int point) {
        if(parent[point] == point)
            return point;
        return parent[point] = find(parent[point]); // Path compression
    }

}
