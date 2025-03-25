package com.algorithms.aprenderypractique.CapitalOne.BankingSystem;

import java.util.Map;
import java.util.HashMap;

public class Account {
    String accountId;
    int currBalance;
    long totalOutgoing;

    Map<Integer, Integer> timedBalance;    // <timestamp, balance>

    Account(String accountId) {
        this.accountId = accountId;
        this.currBalance = 0;
        this.totalOutgoing = 0;
        this.timedBalance = new HashMap<>();
    }

    // Add New Entry -> balance at given Timestamp
    void addBalanceEntry(int timestamp, int balance) {
        timedBalance.put(timestamp, balance);
    }

}