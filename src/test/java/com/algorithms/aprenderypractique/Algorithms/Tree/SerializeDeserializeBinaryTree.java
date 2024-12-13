package com.algorithms.aprenderypractique.Algorithms.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 *  https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *  https://www.youtube.com/watch?v=u4JAi2JJhI8
 */
public class SerializeDeserializeBinaryTree extends BaseTest {

    @Test
    public void test() {
        BinaryTree tree = new BinaryTree(20);
        tree.left = new BinaryTree(8);
        tree.right = new BinaryTree(22);
        tree.left.left = new BinaryTree(4);
        tree.left.right = new BinaryTree(12);
        tree.left.right.left = new BinaryTree(10);
        tree.left.right.right = new BinaryTree(14);

        String ser = serialize(tree);           //  20,8,4,N,N,12,10,N,N,14,N,N,22,N,N
        System.out.println(ser);
        BinaryTree ans = deserialize(ser);
        System.out.println(ans.val);
    }

// Encodes a tree to a single string.
    public String serialize(BinaryTree root) {
        if(root == null)    return "N";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    public BinaryTree deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(nodes);
    }

// Decodes your encoded data to tree.
    public BinaryTree deserializeHelper(Queue<String> nodes) {
        String value = nodes.poll();
        if("N".equals(value))       return null;

        BinaryTree node = new BinaryTree(Integer.parseInt(value));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        return node;
    }


///////////////////////////////////////////////////

    public String serialize2(BinaryTree root) {
        List<String> serialize = new ArrayList<>();
        preOrderTraversal(root, serialize);
        return String.join(",", serialize);
    }

    public void preOrderTraversal(BinaryTree root, List<String> serialize) {
        if(root == null) {
            serialize.add("N");
            return;
        }

        serialize.add(String.valueOf(root.val));
        preOrderTraversal(root.left, serialize);
        preOrderTraversal(root.right, serialize);
    }

    int index;
    public BinaryTree deserialize2(String data) {
        index = 0;
        return deserializePreOrderTraversal(data.split(","));
    }

    public BinaryTree deserializePreOrderTraversal(String[] token) {
        if(index >= token.length || Objects.equals(token[index], "N"))
            return null;

        BinaryTree root = new BinaryTree(Integer.parseInt(token[index]));
        index++;  root.left = deserializePreOrderTraversal(token);
        index++;  root.right = deserializePreOrderTraversal(token);
        return root;
    }

}
