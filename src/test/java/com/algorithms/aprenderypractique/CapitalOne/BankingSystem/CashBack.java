package com.algorithms.aprenderypractique.CapitalOne.BankingSystem;

public class CashBack {
    long scheduledTimestamp;
    String accountId;
    String paymentId;
    int transactionAmount;
    int cashbackApply;

    CashBack(long scheduledTimestamp, String accountId, String paymentId, int transactionAmount, int cashbackApplied) {
        this.scheduledTimestamp = scheduledTimestamp;
        this.accountId = accountId;
        this.paymentId = paymentId;
        this.transactionAmount = transactionAmount;
        this.cashbackApply = cashbackApplied;
    }
}
