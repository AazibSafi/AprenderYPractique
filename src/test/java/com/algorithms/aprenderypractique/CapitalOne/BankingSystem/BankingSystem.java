package com.algorithms.aprenderypractique.CapitalOne.BankingSystem;

import java.util.*;

// using OOP
public class BankingSystem {

    private final Map<String, Account> accounts;   // <accountId, Account>
    private final Map<String, String> mergedAccounts;   // <fromAccount, toAccount>
    private final Map<String, Map<String, PaymentInfo>> accountPayments;  // <accountId, <paymentId, PaymentInfo>>
    private final PriorityQueue<CashBack> cashbackQueue;
    private int paymentCounter;

    public BankingSystem() {
        this.accounts = new HashMap<>();
        this.mergedAccounts = new HashMap<>();
        this.accountPayments = new HashMap<>();
        this.paymentCounter = 0;
        this.cashbackQueue = new PriorityQueue<>(
            (c1, c2) -> Long.compare(c2.scheduledTimestamp, c1.scheduledTimestamp));
    }

    public String resolveAccount(String accountId) {
        while(mergedAccounts.containsKey(accountId)) {
            accountId = mergedAccounts.get(accountId);
        }
        return accountId;
    }

    public void processCashback(int timestamp) {
        while(!cashbackQueue.isEmpty() && cashbackQueue.peek().scheduledTimestamp <= timestamp) {
            CashBack cashBack = cashbackQueue.poll();
            String activeAccountId = resolveAccount(cashBack.accountId);
            Account account = accounts.get(activeAccountId);

            account.currBalance += cashBack.transactionAmount * (cashBack.cashbackApply/100);   // apply cashback 2% of transactionAmount
            account.addBalanceEntry(timestamp, account.currBalance);  // Add new balance entry with Timestamp

            // Update Cashback status
            accountPayments.get(activeAccountId)
                    .get(cashBack.paymentId)
                    .status = PaymentStatus.CASHBACK_RECEIVED;
        }
    }

    public boolean createAccount(int timestamp, String accountId) {
        processCashback(timestamp);

        if(accounts.containsKey(accountId))
            return false;

        Account account = new Account(accountId);
        account.addBalanceEntry(timestamp, 0);  // Add new balance entry with Timestamp
        accounts.put(accountId, account);
        return true;
    }

    public int deposit(int timestamp, String accountId, int amount) {
        processCashback(timestamp);

        String activeAccountId = resolveAccount(accountId);
        Account account = accounts.get(activeAccountId);
        if(account == null)
            return -1;

        account.currBalance += amount;
        account.addBalanceEntry(timestamp, account.currBalance);  // Add new balance entry with Timestamp
        return account.currBalance;
    }

    public boolean withdraw(int timestamp, String accountId, int amount) {
        String activeAccountId = resolveAccount(accountId);
        Account account = accounts.get(activeAccountId);
        if(account == null || amount > account.currBalance)
            return false;

        account.currBalance -= amount;
        account.totalOutgoing += amount;
        account.addBalanceEntry(timestamp, account.currBalance);  // Add new balance entry with Timestamp
        return true;
    }

    public int transfer(int timestamp, String sourceAccountId, String targetAccountId, int amount) {
        processCashback(timestamp);

        sourceAccountId = resolveAccount(sourceAccountId);
        targetAccountId = resolveAccount(targetAccountId);

        Account sourceAccount = accounts.get(sourceAccountId);
        Account targetAccount = accounts.get(targetAccountId);

        if(sourceAccount.equals(targetAccount) ||
                Objects.isNull(sourceAccount) ||
                Objects.isNull(targetAccount) ||
                amount > sourceAccount.currBalance
        )
            return -1;

        sourceAccount.currBalance -= amount;
        targetAccount.currBalance += amount;
        sourceAccount.totalOutgoing += amount;

        // Add Timestamp of balance
        sourceAccount.addBalanceEntry(timestamp, sourceAccount.currBalance);  // Add new balance entry with Timestamp
        targetAccount.addBalanceEntry(timestamp, targetAccount.currBalance);  // Add new balance entry with Timestamp

        return sourceAccount.currBalance;
    }

    public List<String> topSpenders(int timestamp, int n) {
        processCashback(timestamp);
        return accounts.values()
                .stream()
                .sorted((acc1, acc2) -> {
                    if(acc2.totalOutgoing == acc1.totalOutgoing)
                        return acc1.accountId.compareTo(acc2.accountId);
                    return Long.compare(acc2.totalOutgoing, acc1.totalOutgoing);
                })
                .limit(n)
                .map(acc -> acc.accountId + ":" + acc.currBalance)
                .toList();
    }

    public String pay(int timestamp, String accountId, int amount) {
        processCashback(timestamp);

        accountId = resolveAccount(accountId);
        
        // withdraw
        if(!withdraw(timestamp, accountId, amount))
            return "None";

        // "payment(ordinal number of withdrawals from all accounts)". e.g; "payment1", "payment2",
        String paymentId = "payment" + (++paymentCounter);

        // 2% cashback after 24 hours
        long scheduledCashbackTime = timestamp+86400000;
        CashBack cashBack = new CashBack(scheduledCashbackTime, accountId, paymentId, amount, 2);
        cashbackQueue.offer(cashBack);

        // Track the status of Payment
        PaymentInfo paymentInfo = new PaymentInfo(paymentId, scheduledCashbackTime);
        accountPayments.computeIfAbsent(accountId, k -> new HashMap<>())
                .put(paymentId, paymentInfo);

        return paymentId;
    }

    public String getPaymentStatus(int timestamp, String accountId, String payment) {
        processCashback(timestamp);
        accountId = resolveAccount(accountId);

        if(!accounts.containsKey(accountId) ||
                !accountPayments.containsKey(accountId) ||
                !accountPayments.get(accountId).containsKey(payment))
            return "None";

        return accountPayments.get(accountId).get(payment).status.toString();   // "IN_PROGRESS" or "CASHBACK_RECEIVED".
    }

    // mergedAccounts<fromAccount, ToAccount>
    public boolean mergeAccounts(int timestamp, String accountId1, String accountId2) {
        processCashback(timestamp);
        if (accountId1.equals(accountId2))
            return false;

        accountId1 = resolveAccount(accountId1);
        accountId2 = resolveAccount(accountId2);

        if(!accounts.containsKey(accountId1) || !accounts.containsKey(accountId2))
            return false;

        Account target = accounts.get(accountId1);
        Account source = accounts.get(accountId2);

        // Merge balances
        target.currBalance += source.currBalance;
        target.totalOutgoing += source.totalOutgoing;

        // Add new balance entry with Timestamp
        target.addBalanceEntry(timestamp, target.currBalance);

        // Move history
        target.timedBalance.putAll(source.timedBalance);

        // Move payments
        if(accountPayments.containsKey(source.accountId)) {
            accountPayments.get(target.accountId).putAll(accountPayments.get(source.accountId));
            accountPayments.remove(source.accountId);
        }

        // Redirect all future payment ID lookups
        mergedAccounts.put(source.accountId, target.accountId);

        // Reassign cashbacks
        List<CashBack> cashbackList = new ArrayList<>();
        while(!cashbackQueue.isEmpty()) {
            CashBack cashBack = cashbackQueue.poll();
            cashBack.accountId = resolveAccount(cashBack.accountId);

            if(cashBack.accountId.equals(source.accountId))
                cashBack.accountId = target.accountId;

            cashbackList.add(cashBack);
        }
        cashbackQueue.addAll(cashbackList);

        // Remove merged account
        accounts.remove(target.accountId);
        return true;
    }

    public int getBalance(int timestamp, String accountId, int timeAt) {
        processCashback(timestamp);

        accountId = resolveAccount(accountId);

        if(!accounts.containsKey(accountId))    // if account doesn't exist
            return -1;

        Account account = accounts.get(accountId);
        return account.timedBalance.getOrDefault(timeAt, -1);
    }

}
