package com.algorithms.aprenderypractique;

import org.junit.Assert;
import org.junit.Test;

/*
 *  Aazib Safi
 */
public class Buk extends BaseTest {

    @Test
    public void sortTest() {
        int arr[] = {1, 2, 6, 19, 12, 3};
        int resultArr[] = {19, 1, 12, 1, 6, 2, 3};

        sort(arr);

        Assert.assertEquals(arr,resultArr);
    }

    public void print(int arr[]) {
        for(int x : arr) {
            System.out.println(x);
        }
    }

    // Null check
    // Empty Check
    public void sort(int arr[]) {
        for(int i=0;i<arr.length-1;i++) {
            // find Max / Min
            int max = i, min = i;
            boolean isMax = false;

            for(int j=i+1;j<arr.length;j++) {
                if (i % 2 == 0 && arr[j] > arr[max]) {
                    max = j;
                    isMax = true;
                }
                if (i % 2 == 1 && arr[j] < arr[min]) {
                    min = j;
                }
            }
            if(isMax) swap(arr,i,max);
            else swap(arr,i,min);
        }
    }

    public void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
