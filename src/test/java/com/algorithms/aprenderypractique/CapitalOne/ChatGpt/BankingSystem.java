package com.algorithms.aprenderypractique.CapitalOne.ChatGpt;

import java.util.*;

public class BankingSystem {
    private static class Account {
        String accountId;
        int balance;
        long totalOutgoing;

        TreeMap<Long, Integer> balanceHistory;

        Account(String accountId) {
            this.accountId = accountId;
            this.balance = 0;
            this.totalOutgoing = 0;
            this.balanceHistory = new TreeMap<>();
            this.balanceHistory.put(0L, 0); // assume system starts at time 0
        }

        void recordBalance(long timestamp) {
            balanceHistory.put(timestamp, balance);
        }
    }

    private static class Cashback {
        long scheduledTimestamp;
        String originalAccountId;
        String paymentId;
        int cashbackAmount;

        Cashback(long scheduledTimestamp, String originalAccountId, String paymentId, int cashbackAmount) {
            this.scheduledTimestamp = scheduledTimestamp;
            this.originalAccountId = originalAccountId;
            this.paymentId = paymentId;
            this.cashbackAmount = cashbackAmount;
        }
    }

    private static class PaymentInfo {
        String paymentId;
        long scheduledCashbackTime;
        boolean cashbackReceived;

        PaymentInfo(String paymentId, long scheduledCashbackTime) {
            this.paymentId = paymentId;
            this.scheduledCashbackTime = scheduledCashbackTime;
            this.cashbackReceived = false;
        }
    }

    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, String> mergedTo = new HashMap<>(); // old → new
    private PriorityQueue<Cashback> cashbackQueue = new PriorityQueue<>(Comparator.comparingLong(c -> c.scheduledTimestamp));
    private int paymentCounter = 1;
    private Map<String, Map<String, PaymentInfo>> accountPayments = new HashMap<>();

    private void processCashbacks(long timestamp) {
        while (!cashbackQueue.isEmpty() && cashbackQueue.peek().scheduledTimestamp <= timestamp) {
            Cashback cb = cashbackQueue.poll();
            String activeAccountId = resolveAccount(cb.originalAccountId);
            Account acc = accounts.get(activeAccountId);
            if (acc != null) {
                acc.balance += cb.cashbackAmount;
                acc.recordBalance(cb.scheduledTimestamp);

                Map<String, PaymentInfo> payments = accountPayments.get(activeAccountId);
                if (payments != null && payments.containsKey(cb.paymentId)) {
                    payments.get(cb.paymentId).cashbackReceived = true;
                }
            }
        }
    }

    private String resolveAccount(String accountId) {
        while (mergedTo.containsKey(accountId)) {
            accountId = mergedTo.get(accountId);
        }
        return accountId;
    }

    public boolean createAccount(int timestamp, String accountId) {
        processCashbacks(timestamp);
        if (accounts.containsKey(accountId)) return false;

        accounts.put(accountId, new Account(accountId));
        accountPayments.put(accountId, new HashMap<>());
        return true;
    }

    public int deposit(int timestamp, String accountId, int amount) {
        processCashbacks(timestamp);
        accountId = resolveAccount(accountId);
        Account account = accounts.get(accountId);
        if (account == null) return -1;

        account.balance += amount;
        account.recordBalance(timestamp);
        return account.balance;
    }

    public int transfer(int timestamp, String sourceId, String targetId, int amount) {
        processCashbacks(timestamp);
        sourceId = resolveAccount(sourceId);
        targetId = resolveAccount(targetId);

        if (sourceId.equals(targetId)) return -1;
        Account src = accounts.get(sourceId);
        Account tgt = accounts.get(targetId);
        if (src == null || tgt == null || src.balance < amount) return -1;

        src.balance -= amount;
        tgt.balance += amount;
        src.totalOutgoing += amount;

        src.recordBalance(timestamp);
        tgt.recordBalance(timestamp);
        return src.balance;
    }

    public String pay(int timestamp, String accountId, int amount) {
        processCashbacks(timestamp);
        accountId = resolveAccount(accountId);
        Account acc = accounts.get(accountId);
        if (acc == null || acc.balance < amount) return null;

        acc.balance -= amount;
        acc.totalOutgoing += amount;
        acc.recordBalance(timestamp);

        int cashback = (int) (amount * 0.02);
        long cbTime = timestamp + 86400000L;
        String paymentId = "payment" + (paymentCounter++);

        cashbackQueue.offer(new Cashback(cbTime, accountId, paymentId, cashback));
        accountPayments.get(accountId).put(paymentId, new PaymentInfo(paymentId, cbTime));
        return paymentId;
    }

    public String getPaymentStatus(int timestamp, String accountId, String paymentId) {
        processCashbacks(timestamp);
        accountId = resolveAccount(accountId);
        if (!accounts.containsKey(accountId)) return null;

        Map<String, PaymentInfo> payments = accountPayments.get(accountId);
        if (payments == null || !payments.containsKey(paymentId)) return null;

        return payments.get(paymentId).cashbackReceived ? "CASHBACK_RECEIVED" : "IN_PROGRESS";
    }

    public boolean mergeAccounts(int timestamp, String targetId, String sourceId) {
        processCashbacks(timestamp);
        if (targetId.equals(sourceId)) return false;

        targetId = resolveAccount(targetId);
        sourceId = resolveAccount(sourceId);

        if (!accounts.containsKey(targetId) || !accounts.containsKey(sourceId)) return false;

        Account target = accounts.get(targetId);
        Account source = accounts.get(sourceId);

        // Merge balances and history
        target.balance += source.balance;
        target.totalOutgoing += source.totalOutgoing;
        for (Map.Entry<Long, Integer> entry : source.balanceHistory.entrySet()) {
            target.balanceHistory.put(entry.getKey(), entry.getValue()); // Simple merge
        }
        target.recordBalance(timestamp);

        // Move payments
        Map<String, PaymentInfo> sourcePayments = accountPayments.getOrDefault(sourceId, new HashMap<>());
        accountPayments.get(targetId).putAll(sourcePayments);
        accountPayments.remove(sourceId);

        // Redirect all future payment ID lookups
        mergedTo.put(sourceId, targetId);

        // Reassign cashbacks
        List<Cashback> rescheduled = new ArrayList<>();
        while (!cashbackQueue.isEmpty()) {
            Cashback cb = cashbackQueue.poll();
            if (resolveAccount(cb.originalAccountId).equals(targetId)) {
                rescheduled.add(cb);
            } else if (cb.originalAccountId.equals(sourceId)) {
                rescheduled.add(new Cashback(cb.scheduledTimestamp, targetId, cb.paymentId, cb.cashbackAmount));
            } else {
                rescheduled.add(cb);
            }
        }
        cashbackQueue.addAll(rescheduled);

        accounts.remove(sourceId);
        return true;
    }

    public List<String> topSpenders(int timestamp, int n) {
        processCashbacks(timestamp);
        PriorityQueue<Account> pq = new PriorityQueue<>((a, b) -> {
            if (b.totalOutgoing != a.totalOutgoing)
                return Long.compare(b.totalOutgoing, a.totalOutgoing);
            return a.accountId.compareTo(b.accountId);
        });

        pq.addAll(accounts.values());

        List<String> result = new ArrayList<>();
        int count = Math.min(n, pq.size());
        for (int i = 0; i < count; i++) {
            Account acc = pq.poll();
            result.add(acc.accountId + ":" + acc.totalOutgoing);
        }

        return result;
    }

    public Object getBalance(int timestamp, String accountId, long timeAt) {
        processCashbacks(timestamp);
        accountId = resolveAccount(accountId);
        Account acc = accounts.get(accountId);
        if (acc == null) return false;

        Map.Entry<Long, Integer> entry = acc.balanceHistory.floorEntry(timeAt);
        if (entry == null) return false;

        return entry.getValue();
    }

    public int getBalanceNow(String accountId) {
        accountId = resolveAccount(accountId);
        Account acc = accounts.get(accountId);
        return acc != null ? acc.balance : -1;
    }

}
