package com.algorithms.aprenderypractique.algorithm.arrays.LineSweep;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *  https://leetcode.com/problems/points-that-intersect-with-cars
 *
 *  https://leetcode.com/problems/points-that-intersect-with-cars/solutions/4577770/java-2ms-90-line-sweep-clean-code/
 */
public class PointsThatIntersectWithCars extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(7, numberOfPoints(new int[][]{{3,6},{1,5},{4,7}}));
        Assert.assertEquals(7, numberOfPoints(new int[][]{{1,3},{5,8}}));
    }

    public int numberOfPoints(int[][] nums) {
        Set<Integer> set = new HashSet<>();

        for(int[] parking : nums) {
            int start = parking[0];
            int end = parking[1];

            while(start<=end)
                set.add(start++);
        }
        return set.size();
    }

    public int numberOfPoints2(int[][] nums) {
        int[] parkedCars = getParkedCars(nums);
        int points = 0, coverage = 0;
        for (int i = 1; i <= 100; i++) {
            coverage += parkedCars[i];
            if (coverage > 0) {
                points++;
            }
        }
        return points;
    }

    private int[] getParkedCars(int[][] cars) {
        int[] parkedCars = new int[102];
        for (int[] car : cars) {
            parkedCars[car[0]]++;
            parkedCars[car[1] + 1]--;
        }
        return parkedCars;
    }

}
