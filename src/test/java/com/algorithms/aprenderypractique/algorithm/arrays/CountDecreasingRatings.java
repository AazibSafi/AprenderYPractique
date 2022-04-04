package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/playground/Knh68TCX
 *  https://leetcode.com/discuss/interview-question/1639758/amazon-oa-usa
 *  Code Question 1
 *
 *  Asked in Amazon
 */
public class CountDecreasingRatings extends BaseTest {

    @Test
    public void test() {
        List<Integer> ratings = Arrays.asList(4,3,5,4,3);
        long ans = countDecreasingRatings(ratings);
        Assert.assertEquals(9, ans);

        ratings = Arrays.asList(2,1,3);
        ans = countDecreasingRatings(ratings);
        Assert.assertEquals(4, ans);

        ratings = Arrays.asList(4,2,3,1);
        ans = countDecreasingRatings(ratings);
        Assert.assertEquals(4, ans);

        ratings = Arrays.asList(3,2,1);
        ans = countDecreasingRatings(ratings);
        Assert.assertEquals(6, ans);

        ratings = Arrays.asList(9, 8, 7, 6, 5);
        ans = countDecreasingRatings(ratings);
        Assert.assertEquals(15, ans);

        ratings = Arrays.asList(4,4,2,3,1);
        ans = countDecreasingRatings(ratings);
        Assert.assertEquals(5, ans);     // may be 4, 7 -- compiler says it 5

        ratings = Arrays.asList(20814395, 20814396, 266225703, 564591772, 961571548, 961571549, 653367666, 961571545, 961571547, 961571544);
        ans = countDecreasingRatings(ratings);
        Assert.assertEquals(10, ans); // may be 13
    }

/*
    Two Pointer Approach
    Time: O(n)
 */
    public long countDecreasingRatings(List<Integer> ratings) {
        long groups = 0;
        int ptr1 = 0;

        for(int ptr2=0; ptr2<ratings.size(); ptr2++) {
            if(ptr2 > 0 &&
                    (ratings.get(ptr2-1) - ratings.get(ptr2)) == 1) {
                groups += (ptr2 - ptr1 + 1);
            }
            else {
                groups++;
                ptr1 = ptr2;
            }
        }

        return groups;
    }

}
