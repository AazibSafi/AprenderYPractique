package com.algorithms.aprenderypractique.Klarna;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.PrintHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task2 extends BaseTest {

    @Test
    public void test() {
        List<String> transactions = Arrays.asList("John,Doe,john@doe.com,200,TR0001");
        List<String> rejectedTransactions = Task2.findRejectedTransactions(transactions, 200);

        PrintHelper.printList(rejectedTransactions);
    }

    public static List<String> findRejectedTransactions(List<String> transactions, int creditLimit) {

        List<String> rejectedTransactions = new ArrayList<>();
        Map<String,Integer> creditLines = new LinkedHashMap<>();

        for(String transaction : transactions) {

            String[] consumerDetails = transaction.split(",");
            String consumer = consumerDetails[0]+consumerDetails[1]+consumerDetails[2];
            int amount = Integer.parseInt(consumerDetails[3]);
            String txnId = consumerDetails[4];

            int totalPurchases = creditLines.getOrDefault(consumer,0) + amount;

            if(totalPurchases > creditLimit) {
                // Rejected
                rejectedTransactions.add(txnId);
            }
            else {
                // Approved
                creditLines.put( consumer , totalPurchases );
            }

        }

        return rejectedTransactions;
    }

}
