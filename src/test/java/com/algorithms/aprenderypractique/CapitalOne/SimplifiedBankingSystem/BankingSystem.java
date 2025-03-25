package com.algorithms.aprenderypractique.CapitalOne.SimplifiedBankingSystem;

import java.util.*;

// Simplified Version
public class BankingSystem {
    Map<String, Integer> accountBalance;    // <accountId, balance>
    Map<String, Integer> accountActivity;   // <accountId, activities>

    BankingSystem() {
        accountBalance = new HashMap<>();
        accountActivity = new HashMap<>();
    }

    boolean createAccount(String accountId) {
        if(accountBalance.containsKey(accountId))
            return false;
        accountBalance.put(accountId, 0);
        accountActivity.put(accountId, 1);
        return true;
    }

    int deposit(String accountId, int amount) {
        if(!accountBalance.containsKey(accountId))
            return -1;

        accountActivity.merge(accountId, 1, Integer::sum);
        return accountBalance.merge(accountId, amount, Integer::sum);
    }

    int transfer(String sourceAccountId, String targetAccountId, int amount) {
        if(sourceAccountId.equals(targetAccountId) ||
                !accountBalance.containsKey(sourceAccountId) ||
                !accountBalance.containsKey(targetAccountId) ||
                amount > accountBalance.get(sourceAccountId)
        )
            return -1;

        accountBalance.merge(targetAccountId, amount, Integer::sum);
        accountActivity.merge(sourceAccountId, 1, Integer::sum);
        accountActivity.merge(targetAccountId, 1, Integer::sum);
        return accountBalance.merge(sourceAccountId, -amount, Integer::sum);
    }

    List<String> topActivity(int n) {
        return accountActivity.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by activity descending
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }
}