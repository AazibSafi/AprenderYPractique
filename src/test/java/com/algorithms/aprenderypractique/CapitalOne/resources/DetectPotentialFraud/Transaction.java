package com.algorithms.aprenderypractique.CapitalOne.resources.DetectPotentialFraud;

public class Transaction {
    String id;
    String type;
    double amount;
    int timestamp;

    public Transaction(String id, String type, double amount, int timestamp) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
