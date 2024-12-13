package com.algorithms.aprenderypractique.Algorithms.Sorting;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://www.youtube.com/watch?v=PgBzjlCcFvc
 *
 *     Time Complexity:
 *          Best:       O(nLogN)
 *          Average:    O(nLogN)
 *          Worst:      O(n^2)
 **/
public class QuickSort extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{10,80,30,90,40,50,70};
        quickSort(arr,0,arr.length-1);
        Assert.assertArrayEquals(new int[]{10,30,40,50,70,80,90},arr);

        arr = new int[]{};
        quickSort(arr,0,arr.length-1);
        Assert.assertArrayEquals(new int[]{},arr);

        arr = new int[]{1,3,2,4,5,8};
        quickSort(arr,0,arr.length-1);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,8},arr);
    }

    public void quickSort(int[] A, int low, int high) {
        if(low<high) {
            int pivot = partition(A,low,high);
            quickSort(A,low,pivot-1);
            quickSort(A,pivot+1,high);
        }
    }

    public int partition(int[] A, int low, int high) {
        int i=low;
        int pivot = A[high];

        for(int j=low; j<=high-1; j++) {
            if(A[j] < pivot) {
                swap(A,i,j);
                i++;
            }
        }

        swap(A,i,high);     // Place the pivot element into correct position
        return i;       // return pivot index
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
