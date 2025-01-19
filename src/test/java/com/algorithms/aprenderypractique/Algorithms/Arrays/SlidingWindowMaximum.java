package com.algorithms.aprenderypractique.Algorithms.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 *  https://leetcode.com/problems/sliding-window-maximum/
 *  https://www.youtube.com/watch?v=LiSdD3ljCIE
 */
public class SlidingWindowMaximum {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testMaxSlidingWindow(int[] nums, int k, int[] expected) {
        Assertions.assertArrayEquals(expected, maxSlidingWindow(nums, k));
        Assertions.assertArrayEquals(expected, maxSlidingWindow_Heap(nums, k));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7}),
                Arguments.of(new int[]{2, 9, 3, 8, 1, 7, 12, 6, 14, 4, 32, 0, 7, 19, 8, 12, 6}, 4, new int[]{9, 9, 8, 12, 12, 14, 14, 32, 32, 32, 32, 19, 19, 19}),
                Arguments.of(new int[]{1, 2, 3, 4}, 3, new int[]{3, 4}),
                Arguments.of(new int[]{8, 7, 6, 9}, 2, new int[]{8, 7, 9}),
                Arguments.of(new int[]{8, 7, 5, 6}, 2, new int[]{8, 7, 6}),
                Arguments.of(new int[]{1, 1, 1, 1, 1, 4, 5}, 6, new int[]{4, 5}),
                Arguments.of(new int[]{5, 1, 1, 1, 1, 4, 4}, 6, new int[]{5, 4}),
                Arguments.of(new int[]{1, -1}, 1, new int[]{1, -1}),
                Arguments.of(new int[]{4, -2}, 2, new int[]{4}),
                Arguments.of(new int[]{}, 2, new int[0])
        );
    }


/*
    Efficient Algorithm in Linear complexity
    Time Complexity: O(n)
    Space Complexity: O(k)

    Deque -- Doubly Linked List
 */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;

        if(n==0 || k>n)     return new int[0];

        Deque<Integer> deque = new ArrayDeque<>();
        int[] output = new int[n - k + 1];
        int ind=0;

        for(int i=0; i<n; i++) {
            if(!deque.isEmpty() && deque.peekFirst() <= i-k) {
                deque.pollFirst();      // Remove elements from front of deque whose index is out of window - Remove index with out of bound
            }

//  Maintaining the Deque in DESC order
            while(!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();       // Remove all the elements  from deque last who are smaller than the current element
            }

            deque.offerLast(i);     // enqueue current element index

            if(i >= k-1) {          // add the front of deque in the output if the window size is K - (This is to ignore the starting k-1 elements)
                output[ind++] = nums[deque.peekFirst()];
            }
        }

        return output;
    }

/*
    Using Heap
    Time: O(N*logK)     Where logK is the Heap complexity
    worst: O(N*LogN)
 */
    public int[] maxSlidingWindow_Heap(int[] nums, int k) {
        if(nums==null || nums.length==0 || nums.length<k)
            return new int[0];

        int[] output = new int[nums.length - k + 1];        // total number of windows exist ( nums.length - k + 1 )

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int left=0, right=k-1;

        for(int i=0; i<right; i++) {
            maxHeap.add(nums[i]);
        }

        for(int ind=0; right<nums.length; ind++) {
            maxHeap.add(nums[right++]);

            output[ind] = maxHeap.peek().intValue();

            maxHeap.remove(nums[left++]);
        }

        return output;
    }

}
