package com.algorithms.aprenderypractique.interviews.Robinhood.practice;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Problem: From InterviewDB Questions
    Solution: https://chatgpt.com/c/6805d4f8-3acc-8011-860f-7978d558bed4
 */
public class FractionalShares {

    Map<String, Integer> processOrders(List<Order> orders, Map<String, Integer> inventories) {
        for(Order order : orders) {
            int quantityOfShares;

            // Dollar-Based Orders
            if(order.shares.startsWith("$")) {
                // Number of Shares = Dollar Amount / Price Per Share
                // Number of Shares = (Dollar Amount * 100) / Price Per Share in Cents
                quantityOfShares = ( Integer.parseInt(order.shares.substring(1)) * 100 ) / Integer.parseInt(order.pricePerShareInCent);
            }
            else {
                quantityOfShares = Integer.parseInt(order.shares);
            }

            int inventory = inventories.getOrDefault(order.symbol, 0);

            if(order.type.equals("B")) {    // Buy Orders
                // decrease inventory
                inventory -= quantityOfShares;

                // Always non-negative
                if(inventory < 0)
                    inventory = 0;
            }
            else if(order.type.equals("S")) {   // Sell Orders
                // increase inventory
                inventory += quantityOfShares;

                // flatten inventory
                if(inventory >= 100) {
                    int toSell = inventory / 100;
                    inventory = inventory % 100;
                }
            }

            inventories.put(order.symbol, inventory);
        }
        return inventories;
    }

    @Test
    public void test() {
        List<Order> orders = Arrays.asList(
                new Order("AAPL", "B", "$42", "100"),
                new Order("AAPL", "S", "50", "100"),
                new Order("GOOG", "S", "$200", "200"),
                new Order("GOOG", "B", "30", "200")
        );

        Map<String, Integer> inventories = new HashMap<>();
        inventories.put("AAPL", 99);
        inventories.put("GOOG", 50);

        Map<String, Integer> resultantInventories = processOrders(orders, inventories);
        Map<String, Integer> expectedInventories = new HashMap<>();
        expectedInventories.put("AAPL", 7);
        expectedInventories.put("GOOG", 20);
        Assert.assertEquals(expectedInventories, resultantInventories);
    }

}

@AllArgsConstructor
class Order {
    String symbol;
    String type;
    String shares;
    String pricePerShareInCent;
}
