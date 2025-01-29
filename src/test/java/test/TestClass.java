package test;

import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestClass {

    @Test
    public void test() {
        Assert.assertEquals(4, rob(new int[]{1,2,3,1}));
        Assert.assertEquals(12, rob(new int[]{2,7,9,3,1}));
        Assert.assertEquals(4, rob(new int[]{2,1,1,2}));
        Assert.assertEquals(12, rob(new int[]{2,1,0,9,3,1}));
    }

    int rob(int[] house) {
        int rob = 0;
        int notRob = 0;

        for (int j : house) {    // O(N) / O(1)
            int currRob = notRob + j;
            notRob = Math.max(notRob, rob);
            rob = currRob;
        }

        return Math.max(rob, notRob);
    }

    @Test
    public void test2() {
        Assert.assertTrue(CommonHelper.isEquals(List.of(2, 9, 1), robWithPath(new int[]{2, 1, 0, 9, 3, 1})));
        System.out.println(robWithPath(new int[]{2, 1, 0, 9, 3, 1}));

        System.out.println(robWithPath(new int[]{1, 2, 0, 3, 1}));
    }

    List<Integer> robWithPath(int[] house) {
        List<Integer> path = new ArrayList<>();

        int rob = 0;     // previousRobbed
        int notRob = 0;  // previousNotRobbed

        for(int i=0; i<house.length; i++) {			// O(N) / O(1)
            int currRob = notRob + house[i];	    // 1

            if(rob > notRob) {	// prev is robbed
                notRob = rob;

                if(i > 0)
                    path.add( house[i -1] );  	//  Path output [ 1, 2  ]
            }

            rob = currRob;
        }

        if(rob > notRob) {	// prev is robbed
            notRob = rob;
            path.add( house[house.length-1] );
        }

        return path;
    }

}

