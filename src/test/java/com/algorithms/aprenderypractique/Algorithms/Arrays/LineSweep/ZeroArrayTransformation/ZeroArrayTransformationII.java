package com.algorithms.aprenderypractique.Algorithms.Arrays.LineSweep.ZeroArrayTransformation;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/zero-array-transformation-ii
 *      https://leetcode.com/problems/zero-array-transformation-ii/solutions/6055633/java-python-intuitive-line-sweep-with-range-search-brute-force-to-optimal-liner-to-binary/?envType=company&envId=google&favoriteSlug=google-thirty-days
 */
public class ZeroArrayTransformationII extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(2, minZeroArray(new int[]{2,0,2}, new int[][]{{0,2,1},{0,2,1},{1,1,3}}));
        Assert.assertEquals(2, minZeroArray(new int[]{4,3,2,1}, new int[][]{{1,3,2},{0,2,1}}));
    }

/*
    Approach#2 - Binary Search
    Time: O(nLog(m))
    Space: O(n)
    where n is the size of nums and m is the number of queries
*/
    public int minZeroArray(int[] nums, int[][] queries) {
        if(!isZeroArray(nums, queries, queries.length))
            return -1;

        int left = 0, right = queries.length;
        while(left <= right) {
            int mid = left + (right-left)/2;

            if(isZeroArray(nums, queries, mid))
                right = mid-1;
            else
                left = mid+1;
        }
        return left;
    }

/*
    Approach#1 - Brute Force
    Time: O(n.m) => worst case O(n.m^2)
    Space: O(n)
    where n is the size of nums and m is the number of queries
    #TLE
*/
    public int minZeroArray2(int[] nums, int[][] queries) {
        for(int k=0; k<queries.length; k++) {
            if(isZeroArray(nums, queries, k))
                return k;
        }
        return -1;
    }

    public boolean isZeroArray(int[] nums, int[][] queries, int k) {
        int[] line = new int[nums.length + 1];

        for (int q=0; q<k; q++) {
            line[queries[q][0]] += queries[q][2];
            line[queries[q][1] + 1] -= queries[q][2];
        }

        // check the difference with array Line
        int curr = 0;
        for (int i=0; i<nums.length; i++) {
            curr += line[i];
            if (curr < nums[i]) return false;
        }
        return true;
    }

}
