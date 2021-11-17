package com.algorithms.aprenderypractique.algorithm.arrays.CoinDenomination;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=2903692913051025
 *  https://leetcode.com/discuss/interview-question/999637/facebook-online-change-in-a-foreign-currency
 */
public class ChangeForiegnCurrency extends BaseTest {

    @Test
    public void test() {
        int[] denominations = new int[]{5, 10, 25, 100, 200};
        Assert.assertFalse(canGetExactChange(94, denominations));

        denominations = new int[]{4, 17, 29};
        Assert.assertTrue(canGetExactChange(75, denominations));

        denominations = new int[]{1};
        Assert.assertTrue(canGetExactChange(5, denominations));
    }

    int[] canGetExactChange2(int targetMoney, int[] denominations) {
        int[] output = new int[denominations.length];

        for(int i=denominations.length-1; i>=0; i--) {

            if(denominations[i] <= targetMoney) {
                output[i] = targetMoney / denominations[i];
                targetMoney = targetMoney % denominations[i];
            }

        }
        return output;
    }

    boolean canGetExactChange(int targetMoney, int[] denominations) {
        for(int i=denominations.length-1; i>=0; i--) {
            int remainder = targetMoney % denominations[i];
            if(remainder == 0) {
                return true;
            }
            targetMoney = remainder;
        }
        return false;
    }

}
