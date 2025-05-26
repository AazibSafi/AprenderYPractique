package com.algorithms.aprenderypractique.Algorithms.Arrays;

import java.util.ArrayList;
import java.util.List;

public class PartitionArrayAccordingToGivenPivot {

/*
    Time: O(n)
    Space: O(n)
*/
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> less = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();

        for(int num : nums) {
            if(num < pivot)
                less.add(num);
            else if(num == pivot)
                equal.add(num);
            else if(num > pivot)
                greater.add(num);
        }

        less.addAll(equal);
        less.addAll(greater);

        return less.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

}
