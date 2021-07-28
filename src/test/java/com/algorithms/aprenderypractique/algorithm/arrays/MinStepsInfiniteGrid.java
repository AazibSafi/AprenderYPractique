package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *  https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
 */
public class MinStepsInfiniteGrid extends BaseTest {

    @Test
    public void test() {
        int[] A = new int[]{0,1,1};
        int[] B = new int[]{0,1,2};
//        It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
        System.out.println(coverPoints(A,B));
    }

/*
    Time: O(N)
 */
    public int coverPoints(int[] A, int[] B) {
        int steps = 0;
        int x, y;

        for(int i=0; i<A.length-1; i++) {
            x = Math.abs(A[i] - A[i+1]);
            y = Math.abs(B[i] - B[i+1]);

            steps += Math.max(x, y);
        }

        return steps;
    }

}
