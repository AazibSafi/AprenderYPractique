package com.algorithms.aprenderypractique.algorithm.arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  https://leetcode.com/problems/interval-list-intersections/
 *  https://www.youtube.com/watch?v=Qh8ZjL1RpLI&ab_channel=TECHDOSE
 */
public class IntersectionsIntervals extends BaseTest {

    @Test
    public void test() {
        int[][] firstList = new int[][]{{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = new int[][]{{1,5},{8,12},{15,24},{25,26}};
        Assert.assertArrayEquals(new int[][]{{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}}, intervalIntersection(firstList,secondList));

        firstList = new int[][]{{1,3},{5,9}};
        secondList = new int[][]{};
        Assert.assertArrayEquals(new int[][]{}, intervalIntersection(firstList,secondList));

        firstList = new int[][]{};
        secondList = new int[][]{{4,8},{10,12}};
        Assert.assertArrayEquals(new int[][]{}, intervalIntersection(firstList,secondList));

        firstList = new int[][]{{1,7}};
        secondList = new int[][]{{3,10}};
        Assert.assertArrayEquals(new int[][]{{3,7}}, intervalIntersection(firstList,secondList));
    }

/*
Logic
    Condition: (e2 >= s1) && (e1 >= s2)
    intersection: [max(s1,s2) , min(e1,e2)]

    Time: O(N)
 */
    int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();

        int i=0, j=0;

        while(i<firstList.length && j<secondList.length) {
            int s1 = firstList[i][0];
            int e1 = firstList[i][1];
            int s2 = secondList[j][0];
            int e2 = secondList[j][1];

            if(e2 >= s1 && e1 >= s2) {
                result.add(new int[]{Math.max(s1,s2),Math.min(e1,e2)});
            }

            if(e1 < e2)     i++;
            else            j++;
        }

        return result.toArray(new int[result.size()][]);
    }

}
