package com.algorithms.aprenderypractique.algorithm.arrays.heaps;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *  Similar
 *   @see TopKFrequentElements
 *      https://leetcode.com/problems/top-k-frequent-words
 */
public class TopKFrequentWords extends BaseTest {

    @Test
    public void solution() {
        String[] words = new String[]{"i","love","leetcode","i","love","coding"};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("i","love"), topKFrequent(words,2)));

        words = new String[]{"the","day","is","sunny","the","the","the","sunny","is","is"};
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("the","is","sunny","day"), topKFrequent(words,4)));
    }

/*
    Time: O(nlogn)
*/
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();

        for(String str : words) {
            map.put(str, map.getOrDefault(str,0)+1);
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            minHeap.add(entry);

            if(minHeap.size() > k) minHeap.poll();
        }

        List<String> output = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            output.add(0, minHeap.poll().getKey());
        }
        return output;
    }

}
