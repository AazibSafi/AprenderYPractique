package com.algorithms.aprenderypractique.Algorithms.Graph.CourseSchedule;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  https://leetcode.com/problems/course-schedule
 *  Enum State Concept:  https://www.youtube.com/watch?v=iaaObeAEgxI
 *  Overall Logic Understanding: https://www.youtube.com/watch?v=EgI5nU9etnU
 *
 *  DFS Approach
 */
public class CourseSchedule_I_DFS extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(canFinish(2, new int[][]{{1,0}}));
        Assert.assertFalse(canFinish(2, new int[][]{{1,0},{0,1}}));     // Dead Lock
        Assert.assertFalse(canFinish(20, new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}}));
        Assert.assertTrue(canFinish(8, new int[][]{{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}}));
    }

/*
    Time: O(E+V)
    E - Number of Edges
    V - number of Vertices
OR
    Time: O(n+p)
    n - Number of Courses
    p - number of prerequisites

    Space: O(c)
    c - numCourses
 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] vertices = listOfVertices(numCourses, prerequisites);

        STATE[] cycleDetector = new STATE[numCourses];
        Arrays.fill(cycleDetector, STATE.UNVISITED);

        for(int course = 0; course < numCourses; course++) {
            if(!dfsGraph(course, vertices, cycleDetector))    // Back Edge Set needs to reset each time
                return false;
        }

        return true;
    }

    boolean dfsGraph(int course, ArrayList<Integer>[] vertices, STATE[] cycleDetector) {
        ArrayList<Integer> adjacents = vertices[course];        // All connected Adjacent Courses

        if(cycleDetector[course] == STATE.COMPLETED || adjacents.isEmpty()) {
            cycleDetector[course] = STATE.COMPLETED;
            return true;
        }

        if(cycleDetector[course] == STATE.IN_VISIT)       return false;   // Back Edge Detected / Loop Detected

        cycleDetector[course] = STATE.IN_VISIT;

        for(int adj : adjacents) {
            if( !dfsGraph(adj, vertices, cycleDetector) )
                return false;
        }

        cycleDetector[course] = STATE.COMPLETED;
        return true;
    }

    ArrayList<Integer>[] listOfVertices(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adjacent = new ArrayList[numCourses];     // For each course there is a list of its prerequisites

        for(int i=0; i<numCourses; i++)
            adjacent[i] = new ArrayList<>();       // initialize all arrays

        for(int[] courses : prerequisites)
            adjacent[courses[0]].add(courses[1]);

        return adjacent;
    }

    enum STATE {    UNVISITED, IN_VISIT, COMPLETED   }

}
