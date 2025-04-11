package com.algorithms.aprenderypractique.interviews.CapitalOne.BankingSystem.Solution;

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
