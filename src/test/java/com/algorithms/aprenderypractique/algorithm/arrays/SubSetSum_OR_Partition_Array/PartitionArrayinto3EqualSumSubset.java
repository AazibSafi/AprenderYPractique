package com.algorithms.aprenderypractique.algorithm.arrays.SubSetSum_OR_Partition_Array;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *  https://www.interviewbit.com/problems/partitions/
 *  https://www.youtube.com/watch?v=31W_QU1xJcg
 */
public class PartitionArrayinto3EqualSumSubset extends BaseTest {

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 0, 3};
        Assert.assertEquals(2, partition(array));

        array = new int[]{0,0,0,0};
        Assert.assertEquals(3, partition(array));

        array = new int[]{0,1,-1,0};
        Assert.assertEquals(1, partition(array));

        array = new int[]{0,0,1,-1,0};
        Assert.assertEquals(3, partition(array));

        array = new int[]{6,5,5,2};
        Assert.assertEquals(0, partition(array));

        array = new int[]{5,5,5,0};
        Assert.assertEquals(1, partition(array));

        array = new int[]{5,1,3,2,1,5,1};
        Assert.assertEquals(1, partition(array));
    }

/*
    Time: O(N)
 */
    public int partition(int[] B) {
        int totalSum = Arrays.stream(B).sum();      // Get sum of all elements of Array

        int noOfWays = 0;

        if(totalSum%3 == 0) {

            int oneThird = totalSum/3;
            int twoThird = 2*oneThird;
            int sum = 0;
            int onThirdCount = 0;
            for(int i=0;i<B.length-1;i++) {
                sum += B[i];

                if(sum == twoThird)
                    noOfWays += onThirdCount;

                if(sum == oneThird)
                    onThirdCount++;
            }

        }
        return noOfWays;
    }

}
