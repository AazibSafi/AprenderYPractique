package com.algorithms.aprenderypractique.CapitalOne.resources.DetectPotentialFraud;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private final List<Transaction> transactions;
    private double totalAmount;
    private int transactionCount;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
        this.totalAmount = 0.0;
        this.transactionCount = 0;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        totalAmount += transaction.amount;
        transactionCount++;
    }

    public double averageTransactionAmount() {
        if (transactionCount == 0) {
            return 0.0;
        }
        return totalAmount / transactionCount;
    }

    public List<Transaction> detectFraud(double thresholdMultiplier) {
        List<Transaction> fraudulentTransactions = new ArrayList<>();
        double sum = 0.0;
        int count = 0;

        for (Transaction t : transactions) {
            if (count > 0) {
                double averageAmount = sum / count;
                if (t.amount > thresholdMultiplier * averageAmount) {
                    fraudulentTransactions.add(t);
                }
            }
            sum += t.amount;
            count++;
        }
        return fraudulentTransactions;
    }

}
