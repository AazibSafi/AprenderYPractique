package com.algorithms.aprenderypractique.Algorithms.Tree;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another
 *  https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/solutions/1612105/3-steps/?envType=problem-list-v2&envId=7p55wqm
 *  https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/solutions/5483149/detailed-easy-java-python3-c-solution-19-ms/?envType=problem-list-v2&envId=7p55wqm
 *
 *  https://www.youtube.com/watch?v=JegJNGcwtFg&ab_channel=NeetCodeIO
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother extends BaseTest {

    @Test
    public void solution() {
        BinaryTree tree = new BinaryTree(5);
        tree.left = new BinaryTree(1);
        tree.right = new BinaryTree(2);
        tree.left.left = new BinaryTree(3);
        tree.right.left = new BinaryTree(6);
        tree.right.right = new BinaryTree(4);
        Assert.assertEquals("UURL", getDirections(tree, 3, 6));
    }

/*
    Time: O(n)
    Space: O(m+n)
       where m and n are the lengths of the start and destination paths, respectively
 */
    public String getDirections(BinaryTree root, int startValue, int destValue) {
        StringBuilder pathStartValue = new StringBuilder();
        StringBuilder pathEndValue = new StringBuilder();

        findValueWithPath(root, startValue, pathStartValue);
        findValueWithPath(root, destValue, pathEndValue);
        removeCommonPrefix(pathStartValue, pathEndValue);

        return "U".repeat(pathStartValue.length()) + pathEndValue.toString();
    }

    public Boolean findValueWithPath(BinaryTree root, int val, StringBuilder path) {
        if(root == null)    return false;
        if(root.val == val) return true;

        if(findValueWithPath(root.left, val, path))
            path.insert(0, "L");

        else if(findValueWithPath(root.right, val, path))
            path.insert(0, "R");

        return !path.isEmpty();
    }

    public void removeCommonPrefix(StringBuilder start, StringBuilder end) {
        while(!start.isEmpty() && !end.isEmpty() && start.charAt(0) == end.charAt(0)) {
            start.deleteCharAt(0);
            end.deleteCharAt(0);
        }
    }

}
