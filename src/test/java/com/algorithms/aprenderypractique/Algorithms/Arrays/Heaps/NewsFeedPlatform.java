package com.algorithms.aprenderypractique.Algorithms.Arrays.Heaps;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.*;

/**
 *      Bloomberg Second Round.
 *      Similar to
 *          @see TopKFrequentWords
 */
public class NewsFeedPlatform extends BaseTest {

    @Test
    public void test() {
        NewsFeedPlatform platform = new NewsFeedPlatform();

        // Example usage
        platform.add("META");
        platform.add("GOOGLE");
        platform.add("META");
        platform.add("AMAZON");
        platform.add("GOOGLE");
        platform.add("META");

        int k = 2;
        System.out.println("Top " + k + " companies: " + platform.top(k)); // Should output [META, GOOGLE]
    }

    public NewsFeedPlatform() {
        this.map = new HashMap<>();
    }

    Map<String, Integer> map;

    public void add(String company) {
        map.merge(company, 1, Integer::sum);
    }

    // O(N.Logk)
    public List<String> top(int k) {
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> a.getValue() - b.getValue());

        for(var entry: map.entrySet()) {    // O(n)
            minHeap.add(entry);

            if(minHeap.size() > k)
                minHeap.poll();     // O(Logk)
        }

        List<String> output = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            output.add(minHeap.poll().getKey());
        }
        return output.reversed();
    }

}