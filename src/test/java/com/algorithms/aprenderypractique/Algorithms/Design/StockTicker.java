package com.algorithms.aprenderypractique.Algorithms.Design;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *      https://leetcode.com/discuss/interview-question/900369/bloomberg-onsite-top-k-stocks
 *      https://leetcode.com/discuss/interview-question/964672/Bloomberg-or-Software-Engineering-or-Prepration-List#:~:text=Stock%20Ticking%20(similar%20to%20Leaderboard,stocks%20with%20the%20highest%20volume.
 *      https://leetcode.com/discuss/interview-question/algorithms/124837/stock-ticker
 *
 *      Another Approach of implementation: https://leetcode.com/discuss/interview-question/900369/bloomberg-onsite-top-k-stocks#:~:text=I%20got%20this%20same%20question%2C%20I%20used%20heap%20to%20solve%20it%2C%20however%20I%20think%20treeSet%20is%20better%20since%20it%20has%20an%20iterator%20to%20get%20topK%20stocks%2C%20this%20problem%20is%20solved%20in%20redis%20sorted%20sets%2C%20sorted%20sets%20use%20skipList%20and%20support%20not%20just%20topKstocks%20but%20also%20range%20queries%20like%20stocks%20traded%20in%20volume%201000%2D1500%20etc
 *
 *      Similar
 *      @see Leaderboard
 *
 *     Where N is the number of unique stocks
 *          M is the size of the set of stocks with the same volume
 *          k is the number of top stocks required
 *
 *     Time:
 *         addStocksVolume: O(logN + M)
 *         topKStocks: O(K)
 *     Space: O(N + N) => O(N)
 */
public class StockTicker extends BaseTest {

    @Test
    public void test() {
        StockTicker stockTicker = new StockTicker();
        stockTicker.addStocksVolume("AAPL", 50);
        stockTicker.addStocksVolume("GOOG", 100);
        stockTicker.addStocksVolume("MSFT", 75);
        stockTicker.addStocksVolume("AAPL", 25);

        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("GOOG", "MSFT"), stockTicker.topKStocks(2)));
        stockTicker.addStocksVolume("TSLA", 200);
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("TSLA", "GOOG", "MSFT"), stockTicker.topKStocks(3)));
    }

    Map<String, Integer> stocks;                // O(N)
    TreeMap<Integer, Set<String>> ranks;        // O(N)

    public StockTicker() {
        this.stocks = new HashMap<>();
        this.ranks = new TreeMap<>(Collections.reverseOrder());
    }

    // Function to add or update stock volume
    public void addStocksVolume(String stockSymbol, int price) {
        // If the stock already exists
        if(stocks.containsKey(stockSymbol)) {
            int prevPrice = stocks.get(stockSymbol);
            price = prevPrice + price;              // update stock price

            ranks.get(prevPrice).remove(stockSymbol);   // remove its old volume from the TreeMap
            if(ranks.get(prevPrice).isEmpty())
                ranks.remove(prevPrice);
        }

        // Update the stock volume in the HashMap
        stocks.put(stockSymbol, price);
        ranks.computeIfAbsent(price, k -> new HashSet<>()).add(stockSymbol);
    }

    // Function to get the top k stocks by volume
    public List<String> topKStocks(int k) {
        List<String> result = new ArrayList<>();
        for(var entry : ranks.entrySet()) {
            for(String stockSymbol : entry.getValue()) {
                if(result.size() >= k)   // Stop once we have the top k stocks
                    return result;
                result.add(stockSymbol);
            }
        }
        return result;  // return k top stocks
    }

}