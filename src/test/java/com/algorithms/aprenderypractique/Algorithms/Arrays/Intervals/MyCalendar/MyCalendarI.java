package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MyCalendar;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.TreeMap;

/**
 *  https://leetcode.com/problems/interval-list-intersections/
 *  https://leetcode.com/discuss/interview-question/338948/Facebook-or-Onsite-or-Schedule-of-Tasks
 *
 *  https://www.youtube.com/watch?v=Qh8ZjL1RpLI&ab_channel=TECHDOSE
 *
 *  Time: O(Log(N))
 *  Space: O(N) -> For Calendar TreeMap
 */
public class MyCalendarI extends BaseTest {
    TreeMap<Integer, Integer> calendar;

    public MyCalendarI() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer prevEvent = calendar.floorKey(start),       //  O(log n)
                nextEvent = calendar.ceilingKey(start);     //   O(log n)

        if((prevEvent != null && calendar.get(prevEvent) > start)
                ||  nextEvent != null && nextEvent < end)
            return false;

        calendar.put(start, end);           //  O(log n)
        return true;
    }

}
