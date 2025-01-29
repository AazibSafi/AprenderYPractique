package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

/**
 *      https://leetcode.com/problems/kill-process
 */
public class KillProcess extends BaseTest {

/*
    Time: O(n)
    Space: O(n)
*/
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> processTree = new HashMap<>();      // <Parent pid, List<Child Pid>>
        for(int i=0; i<pid.size(); i++) {
            processTree.computeIfAbsent(ppid.get(i), k->new ArrayList<>()).add(pid.get(i));
        }

        return bfs(processTree, kill);
    }

    public List<Integer> bfs(Map<Integer, List<Integer>> tree, int kill) {
        List<Integer> killedProcesses = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(kill);

        while(!queue.isEmpty()) {
            int pid = queue.poll();
            killedProcesses.add(pid);

            if(tree.containsKey(pid)) {
                for(int childPid : tree.get(pid)) {
                    queue.offer(childPid);
                }
            }
        }
        return killedProcesses;
    }
}
