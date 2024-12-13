package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  https://leetcode.com/problems/binary-trees-with-factors/
 *  Logic: https://www.youtube.com/watch?v=brE7Wx4FffU&ab_channel=CodersCamp
 *  Code: https://www.youtube.com/watch?v=9UrDjy2HtZ8&ab_channel=CodingDecoded
 */
public class BinaryTreeWithFactors extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, numFactoredBinaryTrees(new int[]{2,4}));
        Assert.assertEquals(7, numFactoredBinaryTrees(new int[]{2,4,5,10}));
    }

/*
    Number of trees of root node = (number of trees of LEFT Child) * (number of trees of RIGHT Child)

    Time: O(n^2)
    Space: O(n)

    One Test case is failing on Leetcode
 */
    public int numFactoredBinaryTrees(int[] arr) {
        if(arr == null || arr.length == 0)     return 0;

        Arrays.sort(arr);   // if the given array is not sorted

        Map<Integer, Long> map = new HashMap<>();    //  < arrayItem , number of trees can be formed >

        for(int i=0; i<arr.length; i++) {
            long count = 1L;                  // Each individual number itself is a single node tree

//    n = a * b
            for(int j=0; j<i; j++) {
                if (arr[i] % arr[j] == 0 && map.containsKey(arr[i] / arr[j])) {       //   n % a == 0 && if n/a (which is b) is present in the map
                    int leftChild = arr[j];
                    int rightChild = arr[i] / arr[j];
                    count += map.get(leftChild) * map.get(rightChild);
                }
            }

            map.put(arr[i], count);
        }

//  sum of all NoOfTree of each item
        AtomicInteger totalTrees = new AtomicInteger();
        map.forEach((x,y) -> totalTrees.addAndGet(y.intValue()));
        return totalTrees.get() % (1000000000 + 7);             //   From Question: The answer may be too large so return the answer modulo 10^9 + 7.
    }

}
