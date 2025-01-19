package com.algorithms.aprenderypractique.Algorithms.Arrays.MountainArray;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *      https://leetcode.com/problems/find-indices-of-stable-mountains
 */
public class FindIndicesOfStableMountains extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(3, 4), stableMountains(new int[]{1,2,3,4,5}, 2)));
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(1, 3), stableMountains(new int[]{10,1,10,1,10}, 3)));
        Assert.assertTrue(CommonHelper.isEquals(List.of(), stableMountains(new int[]{10,1,10,1,10}, 10)));
    }

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> stableMountains = new ArrayList<>();

        for(int i=1; i<height.length; i++) {
            if(height[i-1] > threshold)
                stableMountains.add(i);
        }

        return stableMountains;
    }

}
