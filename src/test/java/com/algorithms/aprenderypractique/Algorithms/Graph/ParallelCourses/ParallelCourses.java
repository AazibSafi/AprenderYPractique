package com.algorithms.aprenderypractique.Algorithms.Graph.ParallelCourses;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *      https://leetcode.com/problems/parallel-courses
 */
public class ParallelCourses {

/*
    Time: O(V+E)
        V - number of Vertices
        E - Number of Edges
OR
    Time: O(n+p)
        n - Number of Courses
        p - number of prerequisites

    Space: O(c)
        c - numCourses
 */
    public int minimumSemesters(int N, int[][] relations) {
        // InDegrees
        int[] inDegrees = new int[N+1];
        for(int[] relation : relations) {
            inDegrees[relation[1]]++;
        }

        // Zero Degrees
        Queue<Integer> zeroDegree = new ArrayDeque<>();
        for(int course=1; course<N+1; course++) {
            if(inDegrees[course] == 0)
                zeroDegree.add(course);
        }

        int semester = 0;
        int coursesTaken = 0;

        // BFS on zero degree - level order traversal
        while(!zeroDegree.isEmpty()) {
            semester++;

            int currLevelSize = zeroDegree.size();
            while(currLevelSize-- > 0) {
                int course = zeroDegree.poll();     // course Taken
                coursesTaken++;

                for(int[] relation : relations) {
                    if(course == relation[0]) {   // Look for prevCourse
                        inDegrees[relation[1]]--;

                        if(inDegrees[relation[1]] == 0)
                            zeroDegree.add(relation[1]);
                    }
                }
            }
        }

        // Check if all courses are taken
        return coursesTaken == N ? semester : -1;
    }

}
