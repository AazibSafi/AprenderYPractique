package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/swap-adjacent-in-lr-string
 *
 *  Logic: https://www.youtube.com/watch?v=3_7tsztReyE
 *  https://leetcode.com/problems/swap-adjacent-in-lr-string/solutions/3462893/solution/?envType=problem-list-v2&envId=7p55wqm
 *
 *  Same Problem: https://leetcode.com/problems/move-pieces-to-obtain-a-string
 *  Same Solution works
 */
public class SwapAdjacentInLRString extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(canTransform("RXXLRXRXL", "XRLXXRRLX"));
        Assert.assertTrue(canTransform("XXXXXLRXX", "LXXXXXXRX"));
        Assert.assertFalse(canTransform("X", "L"));
        Assert.assertFalse(canTransform("R_L_", "__LR"));
        Assert.assertFalse(canTransform("_R", "R_"));
    }

/*
    Time: O(n)
    Space: O(1)
*/
    public boolean canTransform(String start, String end) {
        if(start.replace("X","").length() != end.replace("X","").length())
            return false;

        char piece = 'X';

        int m = start.length(), n = end.length();
        int i = 0, j = 0;

        while (i < m || j < n) {
            while (i<m && start.charAt(i) == piece)   i++;

            while (j<n && end.charAt(j) == piece)   j++;

            if (i == m || j == n)   break;

            if (start.charAt(i) != end.charAt(j)
                    || (start.charAt(i) == 'R' && i > j)
                    || (start.charAt(j) == 'L' && i < j)) {
                return false;
            }

            i++; j++;
        }
        return i == j;
    }

}
