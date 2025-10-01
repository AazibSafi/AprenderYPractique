package com.algorithms.aprenderypractique.InterviewDB.Pinterest.AlgebraicTarget;

import java.util.HashMap;
import java.util.Map;

/*
    Pinterest Problem

    🔹Effectiveness
        Follow-up 1 (positives only): Pruning is a big win, avoids exploring useless branches.
                    With negatives: Pruning is unsafe → rely only on memoization.
 */
public class AlgebraicTarget_WithoutPrecedence {

    public boolean canReachTarget(int[] nums, int target) {
        Map<String, Boolean> memo = new HashMap<>();
        if(nums.length == 0)    return false;
        return dfs(nums, 1, nums[0], target, memo);
    }

    private boolean dfs(int[] nums, int index, int current, int target, Map<String, Boolean> memo) {
        // *Pruning: If current result already exceeds target, no need to continue
        // This reduces the exponential search.
        if (current > target) return false;

        if (index == nums.length) {
            return current == target;
        }

        String key = index + "#" + current;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int next = nums[index];

        boolean add = dfs(nums, index + 1, current + next, target, memo);
        boolean multi = dfs(nums, index + 1, current * next, target, memo);
        boolean sub = dfs(nums, index + 1, current - next, target, memo);

        boolean div = false;
        if (index != 0 && current % next == 0) {
            div = dfs(nums, index + 1, current / next, target, memo);
        }

        boolean result = add || multi || sub || div;
        memo.put(key, result);
        return result;
    }

}
