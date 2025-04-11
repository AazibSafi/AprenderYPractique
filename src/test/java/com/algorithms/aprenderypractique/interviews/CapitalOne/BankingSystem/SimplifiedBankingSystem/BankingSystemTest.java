package com.algorithms.aprenderypractique.interviews.CapitalOne.BankingSystem.SimplifiedBankingSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BankingSystemTest {

    @Test
    void testCreateAccount() {
        BankingSystem bank = new BankingSystem();

        assertTrue(bank.createAccount("A1"));
        assertFalse(bank.createAccount("A1")); // Duplicate
    }

    @Test
    void testDeposit() {
        BankingSystem bank = new BankingSystem();

        bank.createAccount("A1");
        assertEquals(100, bank.deposit("A1", 100));
        assertEquals(150, bank.deposit("A1", 50));

        assertEquals(-1, bank.deposit("A2", 30)); // non-existing account
    }

    @Test
    void testTransfer() {
        BankingSystem bank = new BankingSystem();

        bank.createAccount("A1");
        bank.createAccount("A2");

        bank.deposit("A1", 100);

        assertEquals(50, bank.transfer("A1", "A2", 50));
        assertEquals(-1, bank.transfer("A1", "A3", 10)); // target doesn't exist
        assertEquals(-1, bank.transfer("A1", "A1", 10)); // same account
        assertEquals(-1, bank.transfer("A1", "A2", 100)); // insufficient funds
    }

    @Test
    void testTopActivity() {
        BankingSystem bank = new BankingSystem();

        bank.createAccount("A1");
        bank.createAccount("A2");
        bank.createAccount("A3");

        bank.deposit("A1", 100);                // A1 = 2
        bank.deposit("A2", 200);                // A2 = 2
        bank.transfer("A2", "A1", 50);          // A2 = 3, A1 = 3
        bank.deposit("A3", 300);                // A3 = 2
        bank.transfer("A3", "A1", 50);          // A3 = 3, A1 = 4

        List<String> top2 = bank.topActivity(2);

        assertEquals(2, top2.size());
        assertEquals("A1", top2.get(0)); // A1 has 4 activities
        assertTrue(top2.contains("A2") || top2.contains("A3")); // second could be A2 or A3
    }

    @Test
    void testTopActivityLessThanN() {
        BankingSystem bank = new BankingSystem();
        bank.createAccount("A1");
        bank.deposit("A1", 50);

        List<String> top = bank.topActivity(5);
        assertEquals(1, top.size());
        assertEquals("A1", top.get(0));
    }

    @Test
    void testTransferDoesNotAffectBalanceOnInvalid() {
        BankingSystem bank = new BankingSystem();
        bank.createAccount("A1");
        bank.createAccount("A2");

        bank.deposit("A1", 20);
        int result = bank.transfer("A1", "A2", 100); // more than balance

        assertEquals(-1, result);
        assertEquals(20, bank.accountBalance.get("A1"));
        assertEquals(0, bank.accountBalance.get("A2"));
    }

}
