package com.algorithms.aprenderypractique.Algorithms.Sorting;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *     Time Complexity:
 *          Best:       O(nLogN)
 *          Average:    O(nLogN)
 *          Worst:      O(nLogN)
 *     (Number of Levels) * (Operations Per Level)  -> nLogN
 *
 *     Space Complexity: O(n)
 **/
public class MergeSort extends BaseTest {

    @Test
    public void test() {
        int[] arr = new int[]{10, 80, 30, 90, 40, 50, 70};
        mergeSort(arr, 0, arr.length-1);
        Assert.assertArrayEquals(new int[]{10, 30, 40, 50, 70, 80, 90}, arr);

        arr = new int[]{12, 35, 87, 26, 9, 28, 7};
        mergeSort(arr, 0, arr.length-1);
        Assert.assertArrayEquals(new int[]{7, 9, 12, 26, 28, 35, 87}, arr);

        arr = new int[]{};
        mergeSort(arr, 0, arr.length-1);
        Assert.assertArrayEquals(new int[]{}, arr);
    }

    public void mergeSort(int[] A, int start, int end) {
        if(start < end) {
            int middle = (start + end) / 2;
            mergeSort(A, start, middle);
            mergeSort(A, middle+1, end);

            merge(A, start, middle, end);      // this will be called LogN times -> O(logN)
        }
    }

//  The Method performs N operations each level of binary tree  -> O(N)
    public void merge(int[] A, int start, int middle, int end) {
        int ind = 0, i = start, j = middle+1;
        int[] tempArray = new int[end-start+1];

        while(i <= middle && j <= end) {
            tempArray[ind++] = (A[i] <= A[j]) ? A[i++] : A[j++];
        }

        while(i <= middle) {
            tempArray[ind++] = A[i++];
        }

        while(j <= end) {
            tempArray[ind++] = A[j++];
        }

        copyArray(A, tempArray, start);
    }

    public void copyArray(int[] A, int[] tempArray, int start) {
        for(int i=0; i<tempArray.length; i++) {
            A[start+i] = tempArray[i];
        }
    }

}
