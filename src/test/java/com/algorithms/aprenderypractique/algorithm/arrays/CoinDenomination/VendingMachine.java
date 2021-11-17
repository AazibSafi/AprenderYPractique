package com.algorithms.aprenderypractique.algorithm.arrays.CoinDenomination;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  A vending machine has the following denominations: 1c, 5c, 10c, 25c, 50c, and $1.
 *     Your task is to write a program that will be used in a vending machine to return change.
 *     Assume that the vending machine will always want to return the least number of coins or notes. Devise a function getChange(M, P)
 *     where M is how much money was inserted into the machine and P the price of the item selected, that returns an array of integers
 *     representing the number of each denomination to return.
 */
public class VendingMachine extends BaseTest {

    @Test
    public void test_Dollar_And_Cents_Mix() {
        int[] denominations = new int[]{1,5,10,25,50,100};    // 5c, 10c, 25c, 50c, and $1

        Assert.assertArrayEquals(new int[]{1,0,0,0,0,4}, getChange(5f, 0.99f,denominations));
        Assert.assertArrayEquals(new int[]{0,1,1,0,0,1}, getChange(3.14f,1.99f,denominations));
        Assert.assertArrayEquals(new int[]{4,0,2,1,1,2}, getChange(3f, 0.01f,denominations));
        Assert.assertArrayEquals(new int[]{1,0,1,1,1,0}, getChange(4f, 3.14f,denominations));
        Assert.assertArrayEquals(new int[]{1,0,1,0,0,0}, getChange(0.45f, 0.34f,denominations));
    }

    int[] getChange(float M, float P, int[] denominations)  {
        int totalMoney = convertToCents(M);
        int itemPrice = convertToCents(P);
        int changeToReturn = totalMoney - itemPrice;

        return getExactChange(changeToReturn, denominations);
    }

    int[] getExactChange(int targetMoney, int[] denominations) {
        int[] output = new int[denominations.length];

        for(int i=denominations.length-1; i>=0; i--) {

            if(denominations[i] <= targetMoney) {
                output[i] = targetMoney / denominations[i];
                targetMoney = targetMoney % denominations[i];
            }

        }
        return output;
    }

    int convertToCents(float dollars) {
        return Math.round(100*dollars);
    }

    @Test
    public void test_Only_Cents() {
        int[] denominations = new int[]{1,5,10,25};

        Assert.assertArrayEquals(new int[]{4,0,0,0},getChange(5, 1,denominations));
        Assert.assertArrayEquals(new int[]{1,0,0,0},getChange(3,2,denominations));
        Assert.assertArrayEquals(new int[]{3,1,0,0},getChange(10, 2,denominations));
        Assert.assertArrayEquals(new int[]{4,0,0,0},getChange(7, 3,denominations));
    }

    int[] getChange(int M, int P, int[] denominations)  {
        return getExactChange(M - P, denominations);
    }

}
