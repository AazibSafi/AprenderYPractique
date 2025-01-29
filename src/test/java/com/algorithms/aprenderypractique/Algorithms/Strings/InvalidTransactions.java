package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 *      https://leetcode.com/problems/invalid-transactions
 *
 *      Todo: I came up with the Bucket Approach if this solution which can give us O(nLogN)
 *      Approach:
 *          - Create Buckets of size 60 i.e; Map<BucketId, List<Transactions>> // Create Transactions Class
 *          - Fill all the transaction into the bucket
 *          - Iterate through each Transaction
 *              - First Condition: if amount exceeds 1000, Add into output invalid Transaction
 *              - get the bucket id based on time/60
 *              - Second Condition:
 *                  - if the List<Transactions> of BucketId has size more than 1 (Which implies that there are another transactions within the range of 60 mnts)
 *                  - And name is same
 *                  - And city is not equals
 *                      then Add into output invalid Transaction
 */
public class InvalidTransactions extends BaseTest {

    @Test
    public void solution() {
        String[] transactions = new String[]{"alice,20,800,mtv","alice,50,100,beijing"};
        List<String> output = Arrays.asList("alice,20,800,mtv","alice,50,100,beijing");
        Assert.assertTrue(CommonHelper.isEquals(output, invalidTransactions(transactions)));

        transactions = new String[]{"alice,20,800,mtv","alice,50,1200,mtv"};
        output = List.of("alice,50,1200,mtv");
        Assert.assertTrue(CommonHelper.isEquals(output, invalidTransactions(transactions)));

        transactions = new String[]{"alice,20,800,mtv","bob,50,1200,mtv"};
        output = List.of("bob,50,1200,mtv");
        Assert.assertTrue(CommonHelper.isEquals(output, invalidTransactions(transactions)));

        transactions = new String[]{"alice,20,800,mtv","alice,50,100,mtv","alice,51,100,frankfurt"};
        output = List.of("alice,20,800,mtv","alice,50,100,mtv","alice,51,100,frankfurt");
        Assert.assertTrue(CommonHelper.isEquals(output, invalidTransactions(transactions)));

        transactions = new String[]{"alice,20,1220,mtv","alice,20,1220,mtv"};
        output = List.of("alice,20,1220,mtv","alice,20,1220,mtv");              // Edge Case
        Assert.assertTrue(CommonHelper.isEquals(output, invalidTransactions(transactions)));

        transactions = new String[]{"bob,627,1973,amsterdam","alex,387,885,bangkok","alex,355,1029,barcelona","alex,587,402,bangkok","chalicefy,973,830,barcelona","alex,932,86,bangkok","bob,188,989,amsterdam"};
        output = List.of("bob,627,1973,amsterdam","alex,387,885,bangkok","alex,355,1029,barcelona");
        Assert.assertTrue(CommonHelper.isEquals(output, invalidTransactions(transactions)));
    }

/*
    Approach#1 - HashTable Data Structure
    Time: O(n^2)
    Space: O(n)
    Where n is the number of transactions
*/
    public List<String> invalidTransactions(String[] transactions) {
        Set<Integer> invalid = new HashSet<>();
/*
    Storing indices of invalid transactions
    In case same multiple transactions are invalid, then they should all be in the output.
*/
        Map<String, List<Item>> map = new HashMap<>();  // <Name, List<{index, time, city}>>

        for(int i=0; i<transactions.length; i++) {
            String[] split = transactions[i].split(",");
            String name = split[0];
            int time = Integer.parseInt(split[1]);
            int amount = Integer.parseInt(split[2]);
            String city = split[3];

            map.computeIfAbsent(name, k -> new ArrayList<>())
                    .add(new Item(time, city, i));

            if(amount > 1000) {
                invalid.add(i);
            }

            for(Item item : map.get(name)) {
                if(!city.equals(item.city) && Math.abs(time - item.time) <= 60) {
                    invalid.add(i);
                    invalid.add(item.index);
                }
            }
        }

        // Return all transaction with the given indices of invalid transactions
        return invalid.stream()
                .map(i -> transactions[i])
                .collect(Collectors.toList());
    }

    static class Item {
        int index;
        int time;
        String city;
        Item(int time, String city, int index) {
            this.time = time;
            this.city = city;
            this.index = index;
        }
    }

}
