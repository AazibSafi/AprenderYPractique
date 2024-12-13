package com.algorithms.aprenderypractique.Algorithms.Arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=192049171861831
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=192049171861831&c=207085731181336&practice_plan=0
 */
public class RevenueMilestone extends BaseTest {

    @Test
    public void test() {
        int[] revenues = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[] milestones = new int[]{100, 200, 500};
        Assert.assertArrayEquals(new int[]{4,6,10}, getMilestoneDays(revenues,milestones));

        revenues = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        milestones = new int[]{100, 200, 500, 3000};
        Assert.assertArrayEquals(new int[]{4,6,10,-1}, getMilestoneDays(revenues,milestones));

        revenues = new int[]{700, 800, 600, 400, 600, 700};
        milestones = new int[]{3100, 2200, 800, 2100, 1000};
        Assert.assertArrayEquals(new int[]{5, 4, 2, 3, 2}, getMilestoneDays(revenues,milestones));
    }

/*
    Time: O(m*r)
 */
    public int[] getMilestoneDays(int[] revenues, int[] milestones) {
        int[] result = new int[milestones.length];

        for(int m=0; m<milestones.length; m++) {
            int sum = 0;

            for(int r=0; r<revenues.length; r++) {
                sum += revenues[r];

                if(sum >= milestones[m]) {
                    result[m] = r+1;
                    break;
                }
            }
            if(result[m] == 0)  result[m] = -1;
        }

        return result;
    }

}
