package com.algorithms.aprenderypractique.interviews.Robinhood.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
    Problem: From InterviewDB Questions
    Solution: https://chatgpt.com/c/680669b8-630c-8011-ba25-3effdb8c6427

    @see com.algorithms.aprenderypractique.Algorithms.Design.DesignAnOrderedStream
 */

/*
    Time: O(n)
    Space: O(n)
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

        // Commit as many consecutive offsets as possible starting from nextExpected
        if(!processed.contains(nextToCommit)) {
            return -1;
        }

        while(processed.contains(nextToCommit)) {
            processed.remove(nextToCommit); // For Space Efficiency
            nextToCommit++;
        }

        // Return the last committed offset in this burst
        return nextToCommit-1;
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(Arrays.asList(2, 0, 5, 4), Arrays.asList(-1, 0, -1, -1)),
                Arguments.of(Arrays.asList(0, 1, 2), Arrays.asList(0, 1, 2)),
                Arguments.of(Arrays.asList(2, 0, 1), Arrays.asList(-1, 0, 2)),
                Arguments.of(Arrays.asList(2,1,0,5,4), Arrays.asList(-1,-1,2,-1,-1))
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testCommitOffsets(List<Integer> input, List<Integer> expectedOutput) {
        OffsetOrdering offsetOrdering = new OffsetOrdering();
        List<Integer> actualOutput = offsetOrdering.commitOffsets(input);

        System.out.println("Input: " + input);
        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

}
