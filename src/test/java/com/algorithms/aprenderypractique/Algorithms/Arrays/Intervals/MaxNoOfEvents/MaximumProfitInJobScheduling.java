package com.algorithms.aprenderypractique.Algorithms.Arrays.Intervals.MaxNoOfEvents;

import com.algorithms.aprenderypractique.BaseTest;
import org.springframework.data.util.Pair;

import java.util.*;

/**
 *      https://leetcode.com/problems/maximum-profit-in-job-scheduling
 *
 *      @see MaximumEarningsFromTaxi
 *      @see MaximumNumberOfEventsThatCanBeAttendedII
 *
 *      Todo: https://leetcode.com/problems/maximum-length-of-pair-chain
 */
public class MaximumProfitInJobScheduling extends BaseTest {

/*
    Approach#2: Sorting + Priority Queue
    Time: O(n * logn)
    Space: O(n)
*/
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // Create Job List
        List<Job> jobs = new ArrayList<>();
        for(int i=0; i<startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }

        // Sort with startTime by ASC order
        jobs.sort(Comparator.comparingInt(job -> job.startTime));

        // min heap having {endTime, profit}
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getFirst));

        int maxProfit = 0;
        for(Job job : jobs) {
            while(!pq.isEmpty() && pq.peek().getFirst() <= job.startTime) {
                maxProfit = Math.max(maxProfit, pq.poll().getSecond());
            }

            // push the job with combined profit
            // if no non-conflicting job is present maxProfit will be 0
            pq.offer(Pair.of(job.endTime, job.profit + maxProfit));
        }

        // update the value of maxProfit by comparing with
        // profit of jobs that exits in the heap
        while(!pq.isEmpty()) {
            maxProfit = Math.max(maxProfit, pq.poll().getSecond());
        }
        return maxProfit;
    }

/*
    Approach#1: Top-down Dynamic Programming

        Sorting: O(nlogn)
        Binary Search: O(logn)
        Memoization Ensure iterate each job once: (n)
    Overall Time: O(n * logn)

        Memoization: O(n)
        Recursive stack: O(n)
    Overall Space: O(n)
*/
    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        // Create Job List
        List<Job> jobs = new ArrayList<>();
        for(int i=0; i<startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }

        // Sort with startTime by ASC order
        jobs.sort(Comparator.comparingInt(job -> job.startTime));

        // Memoization
        int[] memo = new int[startTime.length+1];
        Arrays.fill(memo, -1);

        // Solve the Max profit
        return solve(jobs, 0, memo);
    }

    public int solve(List<Job> jobs, int jobId, int[] memo) {
        if(jobId >= jobs.size()) return 0;

        if(memo[jobId] != -1)   return memo[jobId];

        int nextJobId = findNextJob(jobs, jobId+1, jobs.get(jobId).endTime);
        int pickJob = jobs.get(jobId).profit + solve(jobs, nextJobId, memo);
        int skipJob = solve(jobs, jobId+1, memo);

        return memo[jobId] = Math.max(pickJob, skipJob);
    }

    int findNextJob(List<Job> jobs, int low, int lastJobEndTime) {
        int high = jobs.size()-1, ans = jobs.size();
        while(low <= high) {
            int mid = low + (high-low)/2;
            if(jobs.get(mid).startTime >= lastJobEndTime) {
                ans = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return ans;
    }

    class Job {
        int startTime;
        int endTime;
        int profit;
        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

}
