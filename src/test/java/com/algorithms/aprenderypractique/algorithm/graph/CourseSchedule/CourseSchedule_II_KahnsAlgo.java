package com.algorithms.aprenderypractique.algorithm.graph.CourseSchedule;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 *  https://leetcode.com/problems/course-schedule/
 *  Topological Sort: https://www.youtube.com/watch?v=2l22FRtU45M
 *  Kahn's Algo: https://www.youtube.com/watch?v=cIBFEhD77b4
 *
 *  A directed graph with no cycle is called DAG - Directed Acyclic Graph
 *
 *  Topological sorting is an ordering of the node in a directed graph.
 *  Topological Sort is only possible if the graph is DAG
 *  Topological orderings are NOT unique
 *
 *  Kahn's Algorithm is a simple topological sort algorithm that cn find a topological ordering in O(V+E) time!
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

        int[] inDegrees = getInDegrees(numCourses, prerequisites);
        ArrayList<Integer>[] outDegrees = getOutDegrees(numCourses, prerequisites);
        Queue<Integer> zeroDegree = getZeroDegrees(numCourses, inDegrees);      // Always incoming nodes with no incoming edges

        if(zeroDegree.isEmpty())    return new int[]{};

        while(!zeroDegree.isEmpty()) {
            int pre_course = zeroDegree.poll();
            output[ind++] = pre_course;
            ArrayList<Integer> outDegree = outDegrees[pre_course];

            for(int course : outDegree) {
                inDegrees[course]--;        // Removing edges between pre_course and the current course

                if(inDegrees[course] == 0)
                    zeroDegree.add(course);
            }
        }

        if(ind != numCourses)       // ind should be equal to total number of courses
            return new int[]{};     // Graph contains a cycle

//        for(int inDegree : inDegrees) {
//            if(inDegree != 0)               // Graph contains a cycle
//                return new int[]{};
//        }

        return output;
    }

    int[] getInDegrees(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        for(int[] courses : prerequisites)
            inDegrees[courses[0]]++;
        return inDegrees;
    }

//  inDegree and zeroDegree calculation can be done in single iteration loop

    ArrayList<Integer>[] getOutDegrees(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] outDegrees = new ArrayList[numCourses];

        for(int i=0; i<numCourses; i++)
            outDegrees[i] = new ArrayList<>();       // initialize all arrays

        for(int[] courses : prerequisites)
            outDegrees[courses[1]].add(courses[0]);

        return outDegrees;
    }

    Queue<Integer> getZeroDegrees(int numCourses, int[] inDegrees) {
        Queue<Integer> zeroDegree = new ArrayDeque<>();
        for(int course=0; course<numCourses; course++) {
            if (inDegrees[course] == 0)
                zeroDegree.add(course);
        }
        return zeroDegree;
    }

}
