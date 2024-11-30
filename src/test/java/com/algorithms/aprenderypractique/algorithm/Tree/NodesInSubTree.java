package com.algorithms.aprenderypractique.algorithm.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=3068294883205371
 *  https://leetcode.com/discuss/interview-question/756125/facebook-recruiting-portal-nodes-in-a-subtree
 *
 *          1(a)
 *         /   \
 *       2(b)  3(a)
 *
 */
public class NodesInSubTree extends BaseTest {

    @Test
    public void test() {
        String s = "aba";
        Tree root = new Tree(1);
        root.children.add(new Tree(2));
        root.children.add(new Tree(3));
        List<Query> queries = Arrays.asList(new Query(1, 'a'));
        int[] output = countOfNodes(root, queries, s);

        Assert.assertArrayEquals(new int[]{2},output);


        s = "abaacab";
        root = new Tree(1);
        root.children.add(new Tree(2));
        root.children.add(new Tree(3));
        root.children.add(new Tree(7));
        root.children.get(0).children.add(new Tree(4));
        root.children.get(0).children.add(new Tree(5));
        root.children.get(1).children.add(new Tree(6));
        queries = new ArrayList<>();
        queries.add(new Query(1, 'a'));
        queries.add(new Query(2, 'b'));
        queries.add(new Query(3, 'a'));
        output = countOfNodes(root, queries, s);

        Assert.assertArrayEquals(new int[]{4,1,2},output);
    }

/*
    Time: O(q*n)
    n is no of nodes
    q is no of queries
 */
    public int[] countOfNodes(Tree root, List<Query> queries, String s) {
        List<Integer> result = new ArrayList<>();

        for(Query q : queries) {
            Tree subRoot = findSubRootWithU(root, q.u);
            if(subRoot != null) {
                int count = findCount(subRoot, q.c, s);
                result.add(count);
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }

    private Tree findSubRootWithU(Tree root, int u) {
        if(root == null) return null;
        if(root.val == u) return root;

        for(Tree child : root.children) {
            Tree subRoot = findSubRootWithU(child, u);
            if(subRoot != null) return subRoot;
        }

        return null;
    }

    private int findCount(Tree root, int c, String s) {
        if(root == null) return 0;

        int count = 0;
        if(s.charAt(root.val-1) == c)   count++;

        for(Tree child : root.children) {
            count += findCount(child,c,s);
        }
        return count;
    }

    class Query {
        int u;
        char c;
        Query(int u, char c) {
            this.u = u;
            this.c = c;
        }
    }

}
