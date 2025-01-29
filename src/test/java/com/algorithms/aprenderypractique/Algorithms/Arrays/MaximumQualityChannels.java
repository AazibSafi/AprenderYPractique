package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Arrays;

/**
 *      https://aonecode.com/interview-questions/Channels-Maximum-Quality
 *      https://www.hackerrank.com/challenges/find-the-running-median/problem
 *      https://leetcode.com/discuss/interview-question/1565781/amazon-oa-maximize-the-median-sum
 *      https://leetcode.com/discuss/interview-question/1940397/amazon-online-assessment-find-the-maximum-quality-for-given-input-streamlist-over-n-channels
 */
public class MaximumQualityChannels extends BaseTest {

    @Test
    public void test() {
        int[] packets1 = {10, 20, 30, 40, 50, 60};  int channels = 3;
        System.out.println(getMaxQuality(packets1, channels)); // Expected Output: 115.0

        packets1 = new int[]{10, 20, 30, 40, 50};  channels = 2;
        System.out.println(getMaxQuality(packets1, channels)); // Expected Output: 75.0

        packets1 = new int[]{1, 2, 3, 4, 5, 6};  channels = 3;
        System.out.println(getMaxQuality(packets1, channels)); // Expected Output: 14
    }

/*
    Time: O(nlogn) -> because of sorting
    Auxilary Space: O(n) -> because of sorting
 */
    public double getMaxQuality(int[] packets, int channels) {
        Arrays.sort(packets);   // O(nlogn)  ->  Sort packets to easily pick the largest values

        int n = packets.length;
        int remainingLen = n - (channels - 1);

        // Take (channels-1) largest elements as individual streams
        long sum = 0;
        for(int i=remainingLen; i<n; i++) {     // O(channels-1)
            sum += packets[i];
        }

        // The remaining elements form one channel, find its median
        double median;
        if(remainingLen % 2 == 0) { // Even
            median = ( packets[remainingLen/2 - 1] + packets[remainingLen/2] ) / 2.0;
        }
        else {  // Odd
            median = packets[remainingLen/2];
        }

        return Math.ceil(sum + median);
    }

}
