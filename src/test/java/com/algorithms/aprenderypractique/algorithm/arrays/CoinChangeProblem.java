package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  Coin Changing Minimum Coins Dynamic Programming
 *  https://www.youtube.com/watch?v=NJuKJ8sasGk
 */
public class CoinChangeProblem extends BaseTest {

    @Test
    public void test() {
        int[] coins = new int[]{1,5,6,8};
        List<Integer> minCoins= findMinCoinDenominations(coins,11);
        CommonHelper.printList(minCoins);
        Assert.assertEquals(2,minCoins.size());

        int[] coins2 = new int[]{7,2,3,6};
        minCoins= findMinCoinDenominations(coins2,13);
        CommonHelper.printList(minCoins);
        Assert.assertEquals(2,minCoins.size());
    }

/*
    O(N*M)
 */
    public List<Integer> findMinCoinDenominations(int[] coins, int amount) {
        int[] A = new int[amount+1];        // To find the min number of coins - Length Only
        int[] B = new int[amount+1];        // To find the resultant coins

        A[0] = 0;
        B[0] = -1;
        for(int listIndex=1; listIndex<A.length; listIndex++) {
            A[listIndex] = Integer.MAX_VALUE-1;
            B[listIndex] = -1;
        }

        for(int coinIndex=0; coinIndex<coins.length; coinIndex++) {
            for(int listIndex=1; listIndex<A.length; listIndex++) {

                if( listIndex >= coins[coinIndex] &&
                    A[listIndex-coins[coinIndex]] + 1 < A[listIndex] ) {

                    A[listIndex] = A[listIndex-coins[coinIndex]] + 1;
                    B[listIndex] = coinIndex;

                }
            }
        }

//  for only length of min Coins
//      return A[A.length-1];

        return extractMinCoins(coins, B);
    }

    private List<Integer> extractMinCoins(int[] coins, int[] B) {
        List<Integer> minCoins = new ArrayList<>();
        int n = B.length-1;

        while(n > 0 && B[n] != -1) {
            int coin = coins[ B[n] ];
            minCoins.add( coin );

            n = n - coin;
        }
        return minCoins;
    }

}
