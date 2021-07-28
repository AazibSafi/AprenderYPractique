package com.algorithms.aprenderypractique.algorithm.arrays;

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

    boolean canGetExactChange(int targetMoney, int[] denominations) {
        for(int i=denominations.length-1; i>=0; i--) {
            if(targetMoney % denominations[i] == 0) {
                return true;
            }
            targetMoney = targetMoney % denominations[i];
        }
        return false;
    }

}
