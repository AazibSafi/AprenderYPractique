package com.algorithms.aprenderypractique.algorithm.graph.CourseSchedule;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  https://leetcode.com/problems/course-schedule
 * @see CourseSchedule_I_KahnsAlgo
 */
public class CourseSchedule_II_KahnsAlgo extends BaseTest {

    @Test
    public void solution() {
        Assert.assertArrayEquals(new int[]{0,1} , findOrder(2, new int[][]{{1,0}}));
        Assert.assertArrayEquals(new int[]{0,1,2,3} , findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));  //  {0,2,1,3} is also correct
        Assert.assertArrayEquals(new int[]{0} , findOrder(1, new int[][]{}));
        Assert.assertArrayEquals(new int[]{} , findOrder(2, new int[][]{{0,1},{1,0}}));
    }

/*
    Time: O(V+E)
    E - Number of Edges
    V - number of Vertices
OR
    Time: O(N+P)
    N - Number of Courses
    P - Number of prerequisites
 */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] output = new int[numCourses];
        int ind = 0;

        int[] inDegrees = new int[numCourses];
        for(int[] course : prerequisites) {
            inDegrees[course[0]]++;
        }

        Queue<Integer> zeroDegrees = new ArrayDeque<>();
        for(int i=0; i<numCourses; i++) {
            if(inDegrees[i] == 0)
                zeroDegrees.add(i);
        }

        if(zeroDegrees.isEmpty())   return new int[]{};

        while(!zeroDegrees.isEmpty()) {
            int course = zeroDegrees.poll();    // Take course
            output[ind++] = course;

            for(int[] pre : prerequisites) {
                if(course == pre[1]) {
                    inDegrees[pre[0]]--;        // Removing edges between pre_course and the current course
                    if(inDegrees[pre[0]] == 0)
                        zeroDegrees.add(pre[0]);
                }
            }
        }

        return ind == numCourses ? output : new int[0];
    }

}
