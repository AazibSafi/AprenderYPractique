package com.algorithms.aprenderypractique.Algorithms.Arrays.Heaps;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 *  https://leetcode.com/problems/k-closest-points-to-origin/
 *  https://www.youtube.com/watch?v=VORIA407dB4&ab_channel=TECHDOSE
 *  https://www.youtube.com/watch?v=1rEUgAG7f_c&ab_channel=KevinNaughtonJr.
 *
 *  Note: Some confusion in Time Complexity
 *
 *  Asked in Facebook Final Round
 */
public class K_ClosestPointToOrigin extends BaseTest {

    @Test
    public void solution() {
        int[][] points = new int[][]{{1,3},{-2,2}};
        Assert.assertArrayEquals(new int[][]{{-2,2}}, kClosest(points,1));

        points = new int[][]{{3,3},{5,-1},{-2,4}};
        Assert.assertArrayEquals(new int[][]{{3,3},{-2,4}}, kClosest(points,2));

        points = new int[][]{{-41,72},{53,83},{-95,-31},{-61,68},{32,-56},{16,88},{-81,-48},{-31,56},{-57,-74},{24,-42},{-59,72},{-86,40},{34,-85},{-45,22},{-35,-95}};
        Assert.assertArrayEquals(new int[][]{{24,-42},{-45,22},{-31,56},{32,-56},{-41,72},{16,88},{-61,68},{34,-85},{-59,72}}, kClosest(points,9));
    }

/*
    Using Heap
    Time: O(N*LogK)
    Space: O(K) Heap Size
 */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> distanceFromOrigin(b) - distanceFromOrigin(a));

        for(int[] point : points) {             // O(N)
            maxHeap.add(point);     // O(logk)

            if(maxHeap.size() > k) {
                maxHeap.poll();     // O(logk)
            }
        }

        int[][] output = new int[k][2];
        while(!maxHeap.isEmpty()) {
            output[--k] = maxHeap.poll();
        }

        return output;
    }

//  Euclidean Distance
    int distanceFromOrigin(int[] point) {
        return point[0]*point[0] + point[1]*point[1];       //  (a^2 + b^2)
    }

}
