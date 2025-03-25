package com.algorithms.aprenderypractique.Algorithms.Design;

import com.algorithms.aprenderypractique.BaseTest;

/**
 *      https://leetcode.com/problems/simple-bank-system
 */
public class SimpleBankSystem extends BaseTest {
    long[] balance;
    int n;

    public SimpleBankSystem(long[] balance) {
        n = balance.length;
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if(account1 < 1 || account1 > n || account2 < 1 || account2 > n)
            return false;

        if(withdraw(account1, money)) {
            deposit(account2, money);
            return true;
        }

        return false;
    }

    public boolean deposit(int account, long money) {
        if(account < 1 || account > n)
            return false;

        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if(account < 1 || account > n || balance[account - 1] < money)
            return false;

        balance[account - 1] -= money;
        return true;
    }

}
