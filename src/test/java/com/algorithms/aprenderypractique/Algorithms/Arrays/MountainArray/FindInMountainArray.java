package com.algorithms.aprenderypractique.Algorithms.Arrays.MountainArray;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *      https://leetcode.com/problems/peak-index-in-a-mountain-array
 */
public class FindInMountainArray extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, findInMountainArray(3, Arrays.asList(1,2,3,4,5,3,1)));
        Assert.assertEquals(-1, findInMountainArray(3, Arrays.asList(0,1,2,4,2,1)));
        Assert.assertEquals(100, findInMountainArray(101, Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,100,99,98,97,96,95,94,93,92,91,90,89,88,87,86,85,84,83,82)));
        Assert.assertEquals(2, findInMountainArray(9, Arrays.asList(6,13,9)));
        Assert.assertEquals(0, findInMountainArray(10, Arrays.asList(10,11,16,21,24,30,33,23,21,14)));
        Assert.assertEquals(18, findInMountainArray(116, Arrays.asList(22,23,32,42,46,52,58,61,68,76,77,79,89,95,97,104,106,112,116,120,125,127,137,141,142,148,150,157,148,147,146,136,128,126,116,106,100,93,87,85,84,80,70,65,62,52,49,47,44,34,28,25,15,6,0)));
    }

/*
    Approach - Binary Search
    Time: O(logN)
    Space: O(1)
*/
    public int findInMountainArray(int target, List<Integer> mountainArr) {
        // Find the index of the peak element
        int peak = peakIndexInMountainArray(mountainArr);

        // If peak is the target
        if(mountainArr.get(peak) == target)  return peak;

        // Divide the mountainArr into two arrays based on peak.
        // Target must be in either side of the array.
        // Search in the strictly increasing part of the array. i.e; Left array
        int idx = binarySearch(target, mountainArr, 0, peak-1, "ASC");

        if(idx == -1)
            // Otherwise, search in the strictly decreasing part. i.e; Right array
            idx = binarySearch(target, mountainArr, peak+1, mountainArr.size()-1, "DESC");

        return idx;
    }

    public int binarySearch(int target, List<Integer> M, int start, int end, String order) {
        while(start <= end) {
            int mid = (start + end) / 2;
            if(M.get(mid) == target)    return mid;

            if("ASC".equals(order)) {
                if(M.get(mid) < target) start = mid + 1;
                else                    end = mid - 1;
            }
            else if("DESC".equals(order)) {
                if(M.get(mid) < target) end = mid - 1;
                else                    start = mid + 1;
            }
        }
        return -1;  // Target not found
    }

    /**
     * @see PeakIndexInAMountainArray
     */
    public int peakIndexInMountainArray(List<Integer> M) {
        int left = 0, right = M.size() - 1;
        while(left < right) {
            int mid = (left+right)/2;
            if(M.get(mid) < M.get(mid+1))
                left = mid+1;
            else
                right = mid;
        }
        return left;
    }

}
