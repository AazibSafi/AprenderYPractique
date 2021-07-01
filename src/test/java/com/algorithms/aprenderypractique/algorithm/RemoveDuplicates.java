package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *  Array must be sorted in this Logic
 *  https://www.youtube.com/watch?v=Fm_p9lJ4Z_8
 */
public class RemoveDuplicates extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[] {1,1,2,2,2,3,3,4,4,5,5};
        System.out.println(removeDuplicatesFromSortedArray(array));
    }

/*
    Time Complexity: O(N)
    Space Complexity: O(1)
 */
    public int removeDuplicatesFromSortedArray(int[] arr) {

        if(arr.length==0)       return 0;

        int i=0;

        for(int j=0;j<arr.length;j++) {
            if(arr[i]!=arr[j]) {
                arr[++i] = arr[j];
            }
        }

        return i+1; // length of new array
    }

}
