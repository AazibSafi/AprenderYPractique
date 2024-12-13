package com.algorithms.aprenderypractique.Algorithms.Sorting;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *     Time Complexity:
 *          Average:    O(n^2)
 *          Worst:      O(n^2)
 *
 *     Selection Sort Algorithm is an in-place algorithm, as it does not require extra space.
 **/
public class SelectionSort extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{10, 80, 30, 90, 40, 50, 70};
        selectionSort(arr);
        Assert.assertArrayEquals(new int[]{90, 80, 70, 50, 40, 30, 10}, arr);

        arr = new int[]{12, 35, 87, 26, 9, 28, 7};
        selectionSort(arr);
        Assert.assertArrayEquals(new int[]{87, 35, 28, 26, 12, 9, 7}, arr);

        arr = new int[]{};
        selectionSort(arr);
        Assert.assertArrayEquals(new int[]{}, arr);
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
