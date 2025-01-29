package com.algorithms.aprenderypractique.Algorithms.Graph.CourseSchedule;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  https://leetcode.com/problems/course-schedule-iii
 *  https://www.youtube.com/watch?v=0DiBE3r1dHA
 */
public class CourseSchedule_III extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(3, scheduleCourse(new int[][]{{100,200},{200,1300},{1000,1250},{2000,3200}}));
        Assert.assertEquals(1, scheduleCourse(new int[][]{{1,2}}));
        Assert.assertEquals(2, scheduleCourse(new int[][]{{1,2},{2,3}}));
        Assert.assertEquals(0, scheduleCourse(new int[][]{{3,2},{4,3}}));
        Assert.assertEquals(4, scheduleCourse(new int[][]{{2,5},{2,19},{1,8},{1,3}}));
        Assert.assertEquals(5, scheduleCourse(new int[][]{{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},{2,19}}));
    }

/*
    Time: O(nlogn + nlogn) -> O(nlogn)
    Sapce: O(n) -> queue
 */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));      // O(nlogn)
        PriorityQueue<Integer> previouslyTakenCourses = new PriorityQueue<>((a,b) -> b-a);   // Max heap - Desc
        int time = 0;

        for(int[] course : courses) {   // O(n)
            int duration = course[0];
            int lastDay = course[1];

            if(time + duration <= lastDay) {
                time += duration;
                previouslyTakenCourses.add(duration);
            }
            else if(!previouslyTakenCourses.isEmpty() && previouslyTakenCourses.peek() > duration) {
                time -= previouslyTakenCourses.poll();  //  O(logn) -> heapification
                time += duration;
                previouslyTakenCourses.add(duration);
            }
        }
        return previouslyTakenCourses.size();
    }

}
