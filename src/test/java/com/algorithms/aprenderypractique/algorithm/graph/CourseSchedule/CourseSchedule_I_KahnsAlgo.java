package com.algorithms.aprenderypractique.algorithm.graph.CourseSchedule;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 *  https://leetcode.com/problems/course-schedule
 *  Topological Sort: https://www.youtube.com/watch?v=2l22FRtU45M
 *  Kahn's Algo: https://www.youtube.com/watch?v=cIBFEhD77b4
 *
 *  A directed graph with no cycle is called DAG - Directed Acyclic Graph
 *
 *  Topological sorting is an ordering of the node in a directed graph.
 *  Topological Sort is only possible if the graph is DAG
 *  Topological orderings are NOT unique
 *
 *  Kahn's Algorithm is a simple topological sort algorithm that can find a topological ordering in O(V+E) time!
 */
public class CourseSchedule_I_KahnsAlgo extends BaseTest {

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
 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        for(int[] course : prerequisites) {
            inDegrees[course[0]]++;
        }

        Queue<Integer> zeroDegrees = new ArrayDeque<>();
        for(int i=0; i<numCourses; i++) {
            if(inDegrees[i] == 0)
                zeroDegrees.add(i);
        }

        if(zeroDegrees.isEmpty())   return false;

        while(!zeroDegrees.isEmpty()) {
            int course = zeroDegrees.poll();    // Take course
            numCourses--;

            for(int[] pre : prerequisites) {
                if(course == pre[1]) {
                    inDegrees[pre[0]]--;        // Removing edges between pre_course and the current course
                    if(inDegrees[pre[0]] == 0)
                        zeroDegrees.add(pre[0]);
                }
            }
        }
        // Graph contains a cycle if any inDegree still remains
        return IntStream.of(inDegrees).allMatch(x -> x==0);     // Or return (numCourses == 0); decrease numCourses on each take
    }

}
