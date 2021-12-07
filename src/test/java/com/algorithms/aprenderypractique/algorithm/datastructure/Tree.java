package com.algorithms.aprenderypractique.algorithm.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    public int val;
    public List<Tree> children;

    public Tree() {
        val = 0;
        children = new ArrayList<Tree>();
    }

    public Tree(int _val) {
        val = _val;
        children = new ArrayList<Tree>();
    }

    public Tree(int _val, ArrayList<Tree> _children) {
        val = _val;
        children = _children;
    }

}
