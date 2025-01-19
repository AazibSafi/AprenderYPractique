package com.algorithms.aprenderypractique.Algorithms.Arrays.MissingNumber;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://www.geeksforgeeks.org/find-element-appears-array-every-element-appears-twice
 */
public class SingleElementAmongDoubles extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(2, findSingle(new int[]{2, 3, 5, 4, 5, 3, 4}));
        Assert.assertEquals(5, findSingle(new int[]{2, 5, 2}));
        Assert.assertEquals(3, findSingle(new int[]{3}));
    }

    public int findSingle(int[] arr) {
        int unique = 0;
        for(int x : arr) {
            unique = unique ^ x;
        }
        return unique;
    }

}
