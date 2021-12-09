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
        int[] inDegrees = getInDegrees(numCourses, prerequisites);
        ArrayList<Integer>[] outDegrees = getOutDegrees(numCourses, prerequisites);
        Queue<Integer> zeroDegree = getZeroDegrees(numCourses, inDegrees);      // Always incoming nodes with no incoming edges

        if(zeroDegree.isEmpty())    return false;

        while(!zeroDegree.isEmpty()) {
            int pre_course = zeroDegree.poll();
            ArrayList<Integer> outDegree = outDegrees[pre_course];

            for(int course : outDegree) {       // adjacents
                inDegrees[course]--;

                if(inDegrees[course] == 0)
                    zeroDegree.add(course);
            }
        }

        for(int inDegree : inDegrees) {
            if(inDegree != 0)               // Graph contains a cycle
                return false;
        }

        return true;
    }

    int[] getInDegrees(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        for(int[] courses : prerequisites)
            inDegrees[courses[0]]++;
        return inDegrees;
    }

//  inDegree and zeroDegree calculation can be done in single iteration loop

    Queue<Integer> getZeroDegrees(int numCourses, int[] inDegrees) {
        Queue<Integer> zeroDegree = new ArrayDeque<>();
        for(int course=0; course<numCourses; course++) {
            if (inDegrees[course] == 0)
                zeroDegree.add(course);
        }
        return zeroDegree;
    }

    ArrayList<Integer>[] getOutDegrees(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] outDegrees = new ArrayList[numCourses];

        for(int i=0; i<numCourses; i++)
            outDegrees[i] = new ArrayList<>();       // initialize all arrays

        for(int[] courses : prerequisites)
            outDegrees[courses[1]].add(courses[0]);

        return outDegrees;
    }

}
