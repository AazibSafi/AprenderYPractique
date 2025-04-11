package com.algorithms.aprenderypractique.interviews.Amazon.SDE2;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 *     https://aonecode.com/amazon-online-assessment-cutoff-ranks
 *     https://www.geeksforgeeks.org/number-of-players-whose-rank-is-equal-to-or-less-than-a-given-cutoff-rank/
 *
 *     https://leetcode.com/discuss/interview-question/824381/na-amazon-sde-intern-oa-2
 *
 *     Time Complexity:
 *          Average:    O(n^2)
 *          Worst:      O(n^2)
 *
 *     Selection Sort Algorithm is an in-place algorithm, as it does not require extra space.
 **/
public class CutOffRanks extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{100, 90, 80, 70, 60};
        Assert.assertEquals(2, cutOffRank(2, arr));

        arr = new int[]{100, 100, 80, 70, 60};
        Assert.assertEquals(4, cutOffRank(4, arr));

        arr = new int[]{100, 50, 50, 25};
        Assert.assertEquals(3, cutOffRank(3, arr));

        arr = new int[]{2,2,3,4,5};
        Assert.assertEquals(5, cutOffRank(4, arr));
    }

    public int cutOffRank(int cutOffRank, int[] scores) {
        if(cutOffRank == 0) return 0;
        int[] cache = new int[101];
        for (int n : scores){
            cache[n]++;
        }
        int  res = 0;
        for (int i = 100; i > 0; i--){
            if (cutOffRank <= 0) break;
            cutOffRank -= cache[i];
            res += cache[i];
        }
        return res;
    }

    public int cutOffRanks(int cutOffRank, int[] scores) {

        // Create a min heap of size R
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

        for (int i = 0; i < cutOffRank; i++) {
            heap.add(scores[i]);
        }

        // Traverse the array elements
        for (int i = cutOffRank; i < scores.length; i++) {
            if (scores[i] > heap.peek()) {
                heap.poll();
                heap.add(scores[i]);
            }
        }

        // Return the count of elements in heap
        return heap.size();
    }

    public int cutOffRank1(int cutOffRank, int[] scores) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for(int i : scores) {
            maxHeap.add(i);

            if(maxHeap.size() > cutOffRank)
                maxHeap.remove();
        }
        return maxHeap.size();
    }

    public void selectionSort(int[] arr) {
        int n = arr.length;

        for(int i=0; i<n-1; i++) {
            int max = i;
            for(int j=i+1; j<n; j++) {
                if(arr[j] > arr[max])
                    max = j;
            }

            if(max != i) swap(arr, i, max);
        }
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
