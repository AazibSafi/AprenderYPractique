package com.algorithms.aprenderypractique.Algorithms;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *      https://leetcode.com/problems/exclusive-time-of-functions
 *      https://www.youtube.com/watch?v=3zqVinluGSM
 */
public class ExclusiveTimeOfFunctions extends BaseTest {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{3,4}, exclusiveTime(2, Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6")));
        Assert.assertArrayEquals(new int[]{8}, exclusiveTime(1, Arrays.asList("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7")));
        Assert.assertArrayEquals(new int[]{7,1}, exclusiveTime(2, Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7")));
        Assert.assertArrayEquals(new int[]{8,1}, exclusiveTime(2, Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8")));
        Assert.assertArrayEquals(new int[]{1}, exclusiveTime(1, Arrays.asList("0:start:0","0:end:0")));
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int lastTimeStamp = -1;

        for(String log : logs) {
            String[] token = log.split(":");
            int funcId = Integer.parseInt(token[0]);
            boolean isStart = "start".equals(token[1]);
            int timestamp = Integer.parseInt(token[2]);

            if(!isStart)    timestamp += 1;

            if(!stack.isEmpty()) {
                result[stack.peek()] += (timestamp - lastTimeStamp);
            }

            if(isStart)     stack.push(funcId);
            else            stack.pop();

            lastTimeStamp = timestamp;
        }

        return result;
    }

}
