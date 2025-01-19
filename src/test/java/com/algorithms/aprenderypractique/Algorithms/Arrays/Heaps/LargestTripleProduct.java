package com.algorithms.aprenderypractique.Algorithms.Arrays.Heaps;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=510655302929581
 *  https://www.geeksforgeeks.org/largest-triplet-product-stream/
 *
 *      O(log n) time for the enqueing and dequeing methods (offer, poll, remove() and add)
 *
 *      O(n) for the remove(Object) and contains(Object) methods
 *
 *      O(1) for the retrieval methods (peek, element, and size)
 */
public class LargestTripleProduct extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Assert.assertArrayEquals(new int[]{-1, -1, 6, 24, 60}, findMaxProduct(arr));

        arr = new int[]{2, 1, 2, 1, 2};
        Assert.assertArrayEquals(new int[]{-1, -1, 4, 4, 8}, findMaxProduct(arr));
    }

/*
    Confusion in Overall complexity
 */
    public int[] findMaxProduct(int[] arr) {
        int[] result = new int[arr.length];
        int index = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for(int x : arr) {      // O(n)
            queue.add(x);       // O(logN)

            if(queue.size()<3) {
                result[index++] = -1;
            }
            else {                      // O(n-2)
                int a = queue.poll();   // O(logn)
                int b = queue.poll();
                int c = queue.poll();

                result[index++] = a*b*c;

                queue.add(a);
                queue.add(b);
                queue.add(c);
            }
        }
        return result;
    }

}
