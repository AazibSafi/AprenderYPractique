package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthHighestElement extends BaseTest {

    @Test
    public void test() {
        //int[] array = new int[] {4, 10, 3, 5, 1};
        int[] array = new int[] {2,8,5,3,9,1};
        //System.out.println("Kth Highest Element: " + findKthLargest(array, 3));
        //System.out.println("Kth Smallest Element: " + findKthSmallest(array, 2));
        //heapSort(array);

        int[] arr = new int[]{2,7,11,15};
        int[] res = twoSum(arr,9);
        System.out.println(res[0]+"  "+res[1]);
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int i : nums) {
            minHeap.add(i);

            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        System.out.println(minHeap); //To print all k Largest elements
        return minHeap.remove(); //To print kth Largest element
    }

    // O(N) + O(K*LogN)
    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i : nums) {
            maxHeap.add(i); // O(N)

            if (maxHeap.size() > k) {
                maxHeap.remove();   // O(K*LogN)
            }
        }
        System.out.println(maxHeap); //To print all k Largest elements
        return maxHeap.remove(); //To print kth Largest element
    }
//  {4, 10, 3, 5, 1};
//  {2,8,5,3,9,1}
    public void heapSort(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : nums) {
            maxHeap.add(i); // O(N)
            System.out.println(maxHeap);
        }
        System.out.println("Result: "+maxHeap);
    }

    public int[] twoSum(int[] numbers, int target) {
        int i=0,j=numbers.length-1;
        int[] res = new int[]{-1,-1};
        while(i<j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                res[0] = i;
                res[1] = j;
                return res;
            } else if (sum >= target || numbers[j] >= target) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

}
