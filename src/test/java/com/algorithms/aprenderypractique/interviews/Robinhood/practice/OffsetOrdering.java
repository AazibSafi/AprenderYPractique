package com.algorithms.aprenderypractique.interviews.Robinhood.practice;

import org.junit.Test;

import java.util.*;

/**
    Problem: From InterviewDB Questions
    Solution: https://chatgpt.com/c/680669b8-630c-8011-ba25-3effdb8c6427

    @see com.algorithms.aprenderypractique.Algorithms.Design.DesignAnOrderedStream
 */

public class OffsetOrdering {
    Set<Integer> processed;
    int nextToCommit;

    public OffsetOrdering() {
        this.processed = new HashSet<>();
        this.nextToCommit = 0;
    }

    List<Integer> commitOffsets(List<Integer> offsets) {
        return offsets.stream().map(this::commitOffset).toList();
    }

    int commitOffset(Integer offset) {
        processed.add(offset);

        if(!processed.contains(nextToCommit)) {
            return -1;
        }

        while(processed.contains(nextToCommit)) {
            processed.remove(nextToCommit); // For Space Efficiency
            nextToCommit++;
        }
        return nextToCommit-1;
    }

    @Test
    public void test() {
        OffsetOrdering offsetOrdering = new OffsetOrdering();
        List<Integer> input1 = Arrays.asList(2, 0, 5, 4);
        System.out.println("Input: " + input1);
        System.out.println("Output: " + offsetOrdering.commitOffsets(input1)); // Expected: [-1, 0, 2, -1]

        List<Integer> input2 = Arrays.asList(0, 1, 2);
        System.out.println("Input: " + input2);
        System.out.println("Output: " + offsetOrdering.commitOffsets(input2)); // Expected: [0, 1, 2]

        List<Integer> input3 = Arrays.asList(2, 0, 1);
        System.out.println("Input: " + input3);
        System.out.println("Output: " + offsetOrdering.commitOffsets(input3)); // Expected: [-1, 0, 2]
    }

}
