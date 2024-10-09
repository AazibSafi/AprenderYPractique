package com.algorithms.aprenderypractique.algorithm.arrays.LineSweep;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/maximum-population-year
 *
 *  Reference: https://leetcode.com/discuss/study-guide/2166045/line-sweep-algorithms
 */
public class MaximumPopulationYear extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(1993, maximumPopulation(new int[][]{{1993,1999},{2000,2010}}));
        Assert.assertEquals(1960, maximumPopulation(new int[][]{{1950,1961},{1960,1971},{1970,1981}}));
    }

    public int maximumPopulation(int[][] logs) {
        int[] prefix = new int[101];
        int LM = 1950;

        for(int[] log : logs) {
            prefix[log[0] - LM]++;
            prefix[log[1] - LM]--;
        }

        int maxNum = Integer.MIN_VALUE;
        int sum=0, maxYear=LM;
        for(int i=0;i<prefix.length;i++) {
            sum += prefix[i];
            if(sum > maxNum) {
                maxNum = sum;
                maxYear = LM+i;
            }
        }
        return maxYear;
    }

}
