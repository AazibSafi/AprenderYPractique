package com.algorithms.aprenderypractique.InterviewDB.Pinterest.AlgebraicTarget;

import java.util.HashMap;
import java.util.Map;

/*
    *Pinterest Problem*

    🔹Effectiveness
        Follow-up 1 (positives only): Pruning is a big win, avoids exploring useless branches.
                    With negatives: Pruning is unsafe → rely only on memoization.
 */
public class AlgebraicTarget_WithPrecedence {

    public boolean canReachTarget(int[] nums, int target) {
        Map<String, Boolean> memo = new HashMap<>();
        return dfs(nums, 1, nums[0], nums[0], target, memo);
    }

    /**
     * @param index     current position in nums
     * @param sumSoFar  total sum so far (excluding lastFactor)
     * @param lastFactor last multiplicative chain (to respect precedence)
     * @param target    the target value
     * @param memo      cache of explored states
     */
    private boolean dfs(int[] nums, int index, int sumSoFar, int lastFactor, int target,
                        Map<String, Boolean> memo) {

        // 🔹 Pruning: if all numbers are positive, sumSoFar + lastFactor can't shrink
        if (sumSoFar > target) return false;

        if (index == nums.length) {
            return sumSoFar == target;
        }

        String key = index + "," + sumSoFar + "," + lastFactor;
        if (memo.containsKey(key)) return memo.get(key);

        int next = nums[index];
        boolean result = false;

        // ➕ Case 1: Addition
        if (dfs(nums, index + 1, sumSoFar + next, next, target, memo)) {
            result = true;
        }
        // ✖️ Case 2: Multiplication → adjust lastFactor
        else if (dfs(nums, index + 1, sumSoFar - lastFactor + lastFactor * next, lastFactor * next, target, memo)) {
            result = true;
        }

        memo.put(key, result);
        return result;
    }

}
