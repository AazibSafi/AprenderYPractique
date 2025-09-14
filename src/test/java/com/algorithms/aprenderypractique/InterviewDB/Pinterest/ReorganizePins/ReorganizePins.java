package com.algorithms.aprenderypractique.InterviewDB.Pinterest.ReorganizePins;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @see com.algorithms.aprenderypractique.Algorithms.Strings.ReorganizeString
 *
 * Time: O(NLogC)
 * Space: O(N)
 *
 * where C = number of unique categories, N is the number of pins
 *
 * Pinterest Problem
 */
public class ReorganizePins {

    public static List<String> rearrangePins(List<String> pins) {
        Map<String, Integer> freq = new HashMap<>();
        pins.forEach(pin -> freq.merge(pin, 1, Integer::sum));

        // Max heap by frequency
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue()-a.getValue());
        maxHeap.addAll(freq.entrySet());

        List<String> output = new ArrayList<>();

        // Process heap
        while(maxHeap.size() >= 2) {
            Map.Entry<String, Integer> first = maxHeap.poll();
            Map.Entry<String, Integer> second = maxHeap.poll();

            output.add(first.getKey());
            output.add(second.getKey());

            first.setValue(first.getValue()-1);
            second.setValue(second.getValue()-1);

            if(first.getValue() > 0) maxHeap.offer(first);
            if(second.getValue() > 0) maxHeap.offer(second);
        }

        // If one category remains
        if(!maxHeap.isEmpty()) {
            Map.Entry<String, Integer> last = maxHeap.poll();
            // Impossible to arrange
            if(last.getValue() > 1)
                return new ArrayList<>();   // no solution possible

            output.add(last.getKey());
        }

        return output;
    }

    @Test
    public void testRearrangePinsAllCases() {
        // Case 1: Basic case
        List<String> input1 = Arrays.asList("fashion", "travel", "fashion", "fashion", "book", "music", "travel");
        List<String> output1 = rearrangePins(input1);
        assertEquals(input1.size(), output1.size());
        assertTrue(isValid(output1));

        // Case 2: Single category impossible
        List<String> input2 = Arrays.asList("fashion", "fashion", "fashion");
        List<String> output2 = rearrangePins(input2);
        assertTrue(output2.isEmpty());

        // Case 3: Single pin
        List<String> input3 = Collections.singletonList("fashion");
        List<String> output3 = rearrangePins(input3);
        assertEquals(Collections.singletonList("fashion"), output3);

        // Case 4: Two categories even
        List<String> input4 = Arrays.asList("fashion", "travel", "fashion", "travel");
        List<String> output4 = rearrangePins(input4);
        assertEquals(input4.size(), output4.size());
        assertTrue(isValid(output4));

        // Case 5: Multiple categories
        List<String> input5 = Arrays.asList("a", "a", "b", "b", "c", "c", "d");
        List<String> output5 = rearrangePins(input5);
        assertEquals(input5.size(), output5.size());
        assertTrue(isValid(output5));
    }

    // Helper: ensure no two adjacent items are same
    private boolean isValid(List<String> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).equals(list.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

}
