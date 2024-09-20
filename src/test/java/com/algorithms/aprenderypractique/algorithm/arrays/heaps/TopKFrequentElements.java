package com.algorithms.aprenderypractique.algorithm.arrays.heaps;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**    Similar
 *      @see TopKFrequentWords
 *      https://leetcode.com/problems/top-k-frequent-elements
 *      Todo: https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
 */
public class TopKFrequentElements extends BaseTest {

    @Test
    public void solution() {
        Assert.assertArrayEquals(new int[]{1,2}, topKFrequent(new int[]{1,1,1,2,2,3},2));
        Assert.assertArrayEquals(new int[]{1}, topKFrequent(new int[]{1},1));
    }

/*
    Time: O(nlogn)
*/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int x : nums) {
            map.put(x, map.getOrDefault(x,0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> a.getValue() - b.getValue());

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.add(entry);

            if(minHeap.size() > k) minHeap.poll();
        }

        int[] output = new int[k];
        while(!minHeap.isEmpty() && k>0) {
            output[--k] = minHeap.poll().getKey();
        }
        return output;
    }

}
