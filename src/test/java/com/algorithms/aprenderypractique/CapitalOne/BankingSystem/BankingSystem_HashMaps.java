package com.algorithms.aprenderypractique.CapitalOne.BankingSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Using Hashmaps
public class BankingSystem_HashMaps {

    Map<String, Integer> accountBalance;    // <accountId, balance>
    Map<String, Integer> accountActivity;   // <accountId, activities>
    Map<String, Map<String, String>> paymentStatus;   // <accountId, <payment, status>>

    int totalWithdrawals = 0;

    BankingSystem_HashMaps() {
        accountBalance = new HashMap<>();
        accountActivity = new HashMap<>();
    }

    boolean createAccount(int timestamp, String accountId) {
        if(accountBalance.containsKey(accountId))
            return false;
        accountBalance.put(accountId, 0);
        return true;
    }

    int deposit(int timestamp, String accountId, int amount) {
        if(!accountBalance.containsKey(accountId))
            return -1;
        return accountBalance.merge(accountId, amount, Integer::sum);
    }

    boolean withdraw(int timestamp, String accountId, int amount) {
        if(!accountBalance.containsKey(accountId) || amount > accountBalance.get(accountId))
            return false;
        accountBalance.merge(accountId, -amount, Integer::sum);
        return true;
    }

    int transfer(int timestamp, String sourceAccountId, String targetAccountId, int amount) {
        if(sourceAccountId.equals(targetAccountId) ||
                !accountBalance.containsKey(sourceAccountId) ||
                !accountBalance.containsKey(targetAccountId) ||
                amount > accountBalance.get(sourceAccountId)
        )
            return -1;

        accountBalance.merge(targetAccountId, amount, Integer::sum);
        accountActivity.merge(sourceAccountId, 1, Integer::sum);
        return accountBalance.merge(sourceAccountId, -amount, Integer::sum);
    }

    List<String> topSpenders(int timestamp, int n) {
        return accountActivity.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by activity descending
                .limit(n)
                .map(e -> e.getKey() + ":" + e.getValue())
                .toList();
    }

    String pay(int timestamp, String accountId, int amount) {
        // withdraw
        if(withdraw(timestamp, accountId, amount)) {
            // mark as activity
            accountActivity.merge(accountId, 1, Integer::sum);

            // "payment(ordinal number of withdrawals from all accounts)". e.g; "payment1", "payment2",
            String paymentIdentifier = "payment" + (++totalWithdrawals);

            // Keep the status of payment
            paymentStatus.computeIfAbsent(accountId, k->new HashMap<>())
                    .put(paymentIdentifier, "IN_PROGRESS");

            // 2% cashback after 24 hours
            // TODO

            // return
            return paymentIdentifier;
        }

        return "None";
    }

    String getPaymentStatus(int timestamp, String accountId, String payment) {
        if(!paymentStatus.containsKey(accountId) ||
                !paymentStatus.get(accountId).containsKey(payment))
            return "None";

        return paymentStatus.get(accountId).get(payment);   // "IN_PROGRESS" or "CASHBACK_RECEIVED".
    }

//    boolean mergeAccounts(int timestamp, String accountId1, String accountId2) {
//        if(accountId1.equals(accountId2)
//            || !accountBalance.containsKey(accountId1)
//            || !accountBalance.containsKey(accountId2)
//            )
//            return false;
//
//        // Todo: Merge account
//
//        removeAccount(accountId2);
//        return true;
//    }
//
//    int getBalance(int timestamp, String accountId, int timeAt) {
//        // Todo
//    }

    void removeAccount(String accountId) {
        accountBalance.remove(accountId);
        accountActivity.remove(accountId);
        paymentStatus.remove(accountId);
    }

}