package com.algorithms.aprenderypractique.InterviewDB.Pinterest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bill {

    public int minTransfers(int[][] transactions) {
        // calculate total debt for each
        Map<Integer, Integer> balances = new HashMap<>();
        for(int[] t : transactions) {
            balances.merge(t[0], -t[2], Integer::sum);
            balances.merge(t[1], t[2], Integer::sum);
        }

        List<Integer> debt = balances.values().stream().filter(b -> b!=0).collect(Collectors.toList());

        return dfs(debt, 0);
    }

    int dfs(List<Integer> debt, int curr) {
        int n = debt.size();
        while(curr < n && debt.get(curr) == 0)
            curr++;

        int minTran=0;
        for(int nxt=curr+1; nxt<n; nxt++) {
            if(debt.get(curr) * debt.get(nxt) < 0) {
                debt.set(nxt, debt.get(curr) + debt.get(nxt));
                minTran = Math.min(minTran, 1 + dfs(debt, nxt));
                debt.set(nxt, debt.get(curr) - debt.get(nxt));
            }
        }
        return minTran;
    }

}
