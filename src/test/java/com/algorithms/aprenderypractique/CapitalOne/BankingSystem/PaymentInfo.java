package com.algorithms.aprenderypractique.CapitalOne.BankingSystem;

public class PaymentInfo {
    String paymentId;
    long scheduledCashbackTime;
    PaymentStatus status;

    PaymentInfo(String paymentId, long scheduledCashbackTime) {
        this.paymentId = paymentId;
        this.scheduledCashbackTime = scheduledCashbackTime;
        this.status = PaymentStatus.IN_PROGRESS;
    }
}
