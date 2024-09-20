package com.algorithms.aprenderypractique.algorithm.arrays.Digits;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 *      https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity
 */
public class LargestNumberAfterDigitSwapsByParity extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3412, largestInteger(1234));
        Assert.assertEquals(87655, largestInteger(65875));
    }

    public int largestInteger(int num) {
        String s = String.valueOf(num);

        PriorityQueue<Integer> even = new PriorityQueue<>((a,b) -> b-a);
        PriorityQueue<Integer> odd = new PriorityQueue<>((a,b) -> b-a);

        for(char c : s.toCharArray()) {
            int x = c-'0';
            if(x%2==0)
                even.add(x);
            else
                odd.add(x);
        }

        int ans=0;
        for(char c : s.toCharArray()) {
            ans = ans*10;
            int x = c-'0';
            if(x%2==0)
                ans += even.remove();
            else
                ans += odd.remove();
        }

        return ans;
    }

// Using ArrayList
    public int largestInteger2(int num) {
        String s = String.valueOf(num);

        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();

        for(char c : s.toCharArray()) {
            int x = c-'0';
            if(x%2==0)
                even.add(x);
            else
                odd.add(x);
        }

        even.sort(Collections.reverseOrder());
        odd.sort(Collections.reverseOrder());

        int i=0, j=0, ans=0;
        for(char c : s.toCharArray()) {
            ans = ans*10;
            int x = c-'0';
            if(x%2==0)
                ans += even.get(i++);
            else
                ans += odd.get(j++);
        }

        return ans;
    }

}
