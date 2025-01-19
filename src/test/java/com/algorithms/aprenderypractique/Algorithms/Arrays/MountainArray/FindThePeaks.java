package com.algorithms.aprenderypractique.Algorithms.Arrays.MountainArray;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *      https://leetcode.com/problems/find-the-peaks
 */
public class FindThePeaks extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(CommonHelper.isEquals(List.of(), findPeaks(new int[]{2, 4, 4})));
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(1, 3), findPeaks(new int[]{1, 4, 3, 8, 5})));
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public List<Integer> findPeaks(int[] mountain) {
        return IntStream.rangeClosed(1, mountain.length-1)
                .filter(i -> (mountain[i] > mountain[i-1] && mountain[i] > mountain[i+1]))
                .boxed()    // convert to wrapper objects
                .collect(Collectors.toList());


//        List<Integer> peaks = new ArrayList<>();
//        for(int i=1; i<mountain.length-1; i++) {
//            if(mountain[i] > mountain[i-1] && mountain[i] > mountain[i+1])
//                peaks.add(i);
//        }
//        return peaks;
    }

}
