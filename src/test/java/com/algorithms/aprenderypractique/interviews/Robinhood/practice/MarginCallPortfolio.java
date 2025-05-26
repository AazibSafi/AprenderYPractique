package com.algorithms.aprenderypractique.interviews.Robinhood.practice;

import java.util.*;

/**
    Problem: From InterviewDB Questions
    Solution: https://chatgpt.com/c/68068fed-06c8-8011-ac0b-0607afc2135b
 */
public class MarginCallPortfolio {
    Map<String, Integer> portfolio; // <Symbol, no of Shares>
    Map<String, Integer> priceMap; // <Symbol, price of each share>
    int cash;

    public MarginCallPortfolio() {
        this.portfolio = new TreeMap<>();
        this.priceMap = new HashMap<>();
        this.cash = 1000;
    }

    public List<List<String>> parseTrade(List<List<String>> input) {
        for(List<String> records : input) {
            Trade trade = new Trade(records);
            int total = trade.quantity * trade.price;

            // Update the latest known price
            priceMap.put(trade.symbol, trade.price);

            // Update portfolio and cash
            if(trade.type.equals("B")) {    // Buy
                // decrease cash
                cash -= total;
                portfolio.merge(trade.symbol, trade.quantity, Integer::sum);
            }
            else if(trade.type.equals("S")) {   // Sell
                // increase cash
                cash += total;
                portfolio.merge(trade.symbol, -trade.quantity, Integer::sum);
            }

            // Trigger margin call if necessary
            if(cash < 0) {
                // check Margin Call
                // Verify Collateral stocks
                cash += marginCall(portfolio, priceMap, cash);
            }
        }

        List<List<String>> output = new ArrayList<>();
        output.add(Arrays.asList("CASH", ""+cash));
        portfolio.forEach((symbol, quantity) -> output.add(Arrays.asList(symbol, quantity+"")));
        return output;
    }

    public int marginCall(Map<String, Integer> portfolio, Map<String, Integer> priceMap, int cashDeficit) {
        int recovered = 0;

        // Sort stocks by price descending, then symbol ascending
        List<String> stocks = new ArrayList<>(priceMap.keySet());
        stocks.sort((a,b) -> {
            int priceDiff = priceMap.getOrDefault(b, 0) - priceMap.getOrDefault(a, 0);
            return priceDiff != 0 ? priceDiff : a.compareTo(b);
        });

        // start selling expensive stocks
        for(String symbol : stocks) {
            if(symbol.endsWith("O"))  continue;   // skip collateralized

            int quantity = portfolio.get(symbol);
            int price = priceMap.get(symbol);

            while(quantity > 0 && (cashDeficit + recovered) < 0) {
                quantity--;
                recovered += price;
            }

            if(quantity == 0)   portfolio.remove(symbol);
            else portfolio.put(symbol, quantity);

            if(cashDeficit + recovered >= 0) break;
        }

        return recovered;
    }
}

class Trade {
    int timestamp;
    String symbol;
    String type; // "B" or "S"
    int quantity;
    int price;

    public Trade(List<String> data) {
        this.timestamp = Integer.parseInt(data.get(0));
        this.symbol = data.get(1);
        this.type = data.get(2);
        this.quantity = Integer.parseInt(data.get(3));
        this.price = Integer.parseInt(data.get(4));
    }
}