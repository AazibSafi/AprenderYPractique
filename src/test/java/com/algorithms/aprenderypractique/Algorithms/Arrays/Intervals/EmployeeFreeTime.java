package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 *  https://leetcode.com/problems/employee-free-time/
 *  https://protegejj.gitbook.io/algorithm-practice/leetcode/heap/759-employee-free-time
 */
public class EmployeeFreeTime extends BaseTest {

    @Test
    public void solution() {
        List<List<Interval>> schedule = Arrays.asList(
                    Arrays.asList(new Interval(1,2), new Interval(5,6)),
                List.of(new Interval(1, 3)),
                List.of(new Interval(4, 10))
            );
        List<Interval> result = List.of(new Interval(3, 4));
        Assert.assertTrue(CommonHelper.isEquals(result, employeeFreeTime(schedule)));

        schedule = Arrays.asList(
                Arrays.asList(new Interval(1,3), new Interval(6,7)),
                List.of(new Interval(2, 4)),
                Arrays.asList(new Interval(2,5), new Interval(9,12))
        );
        result = Arrays.asList(new Interval(5,6), new Interval(7,9));
        Assert.assertTrue(CommonHelper.isEquals(result, employeeFreeTime(schedule)));
    }

/*
    Time: O(nLogn)
    Space: O(n)
 */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();

        if(CollectionUtils.isEmpty(schedule))       return res;

        List<Interval> list = new ArrayList<>();

        for (List<Interval> time : schedule) {
            list.addAll(time);
        }

//  Sort the list by start, but if both intervals has equal start, then sort by end
        list.sort((i1, i2) -> i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start);

        PriorityQueue<Integer> endTime = new PriorityQueue<>(Collections.reverseOrder());
        endTime.add(list.get(0).end);

        for (int i = 1; i < list.size(); i++) {
            if (!endTime.isEmpty() && endTime.peek() < list.get(i).start) {
                res.add(new Interval(endTime.peek(), list.get(i).start));
            }
            endTime.add(list.get(i).end);
        }
        return res;
    }

    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

//  These methods are just for this test case Assertions method
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return start == interval.start && end == interval.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

}
