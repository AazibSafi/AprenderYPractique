package com.algorithms.aprenderypractique.Algorithms.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  https://leetcode.com/problems/optimal-account-balancing
 *  Splitwise Problem
 */
public class OptimalAccountBalancing {

    /*
        Time: O((n-1)!)
        Space: O(n)
    */
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> balanceMap = new HashMap<>();

        for(int[] t : transactions) {
            balanceMap.merge(t[0], -t[2], Integer::sum);
            balanceMap.merge(t[1], t[2], Integer::sum);
        }

        List<Integer> debts = balanceMap.values().stream().filter(amount -> amount!=0)
                .collect(Collectors.toList());

        return dfs(debts, 0);
    }

    int dfs(List<Integer> debts, int curr) {
        int n = debts.size();
        while(curr < n && debts.get(curr) == 0)
            curr++;

        if(curr == n)   return 0;

        int minTrans = Integer.MAX_VALUE;
        for(int nxt=curr+1; nxt<n; nxt++) {
            if(debts.get(curr) * debts.get(nxt) < 0) {
                debts.set(nxt, debts.get(nxt) + debts.get(curr));
                minTrans = Math.min(minTrans, 1 + dfs(debts, curr+1));
                debts.set(nxt, debts.get(nxt) - debts.get(curr));
            }
        }
        return minTrans;
    }

}
