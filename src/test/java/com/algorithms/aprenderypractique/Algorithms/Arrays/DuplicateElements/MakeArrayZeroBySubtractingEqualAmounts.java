package com.algorithms.aprenderypractique.Algorithms.Arrays.DuplicateElements;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *      https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts
 *      https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/solutions/2357691/java-c-python-number-of-different-positives/?envType=problem-list-v2&envId=7p5x763&sorting=W3sic29ydE9yZGVyIjoiREVTQ0VORElORyIsIm9yZGVyQnkiOiJGUkVRVUVOQ1kifV0%3D&page=1
 *      https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/solutions/2357658/count-unique-needs-indirect-visualization/?envType=problem-list-v2&envId=7p5x763&sorting=W3sic29ydE9yZGVyIjoiREVTQ0VORElORyIsIm9yZGVyQnkiOiJGUkVRVUVOQ1kifV0%3D&page=1
 */
public class MakeArrayZeroBySubtractingEqualAmounts extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, minimumOperations(new int[]{1,5,0,3,5}));
        Assert.assertEquals(3, minimumOperations(new int[]{5,4,3}));
        Assert.assertEquals(0, minimumOperations(new int[]{0}));
    }

/*
    Time: O(n)
    Space: O(1)
    Approach#3: Same as approach 2 but space efficient
 */
    public int minimumOperations(int[] nums) {
        return (int) Arrays.stream(nums).filter(num -> num > 0).distinct().count();
    }

/*
    Time: O(n)
    Space: O(n) => Although space doesn't matter much bcz as per constraint, max size of nums is 100 so it is always constant. Not large data
    Approach#2: Number of Different Positives
                Number of unique elements = Number of Operations
 */
    public int minimumOperations2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x : nums) {
            if(x != 0)
                set.add(x);
        }
        return set.size();
    }

}

/*
    Approach#1 - Brute Force
        - get the min of Array - O(n)
        - subtract the min number from whole array O(n)
        - Repeat the process until the array becomes ZERO - n times

    Time: O(n) + O(n) => O(n * (n+n)) => O(n^2)
*/