package com.algorithms.aprenderypractique.algorithm.arrays.SmallestNumberOfSumOfDigits;

import  com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.youtube.com/watch?v=H7iqIjbWty4&ab_channel=CodeLibrary
 *  Find the smallest number with given number of digits and sum of digits
 */
public class SmallestNumber_WithNDigits_WhoseSumOfDigits extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(299, findSmallestNumber(3,20));
        Assert.assertEquals(1199, findSmallestNumber(4,20));
        Assert.assertEquals(10199, findSmallestNumber(5,20));
        Assert.assertEquals(18, findSmallestNumber(2,9));
        Assert.assertEquals(108, findSmallestNumber(3,9));
        Assert.assertEquals(1089, findSmallestNumber(4,18));
        Assert.assertEquals(8999, findSmallestNumber(4,35));
        Assert.assertEquals(-1, findSmallestNumber(4,38));      //  Edge Case
        Assert.assertEquals(0, findSmallestNumber(1,0));        //  Edge Case
        Assert.assertEquals(-1, findSmallestNumber(4,0));       //  Edge Case
    }

//  Time: O(d)
    int findSmallestNumber(int d, int sum) {

//  If sum of digits is 0, then a number is possible only if number of digits is 1
        if(sum == 0) {
            if(d == 1)      return 0;
            else            return -1;
        }

//  if we put all the digits as 9, and the sums of that number is still less than the required sum,
//  it implies that the number with d digits can not be formed
        if(d*9 < sum)      return -1;

        int[] nums = new int[d];

        for(int i=d-1; i>=0; i--) {
            if(sum>9) {
                nums[i] = 9;
                sum -= 9;
            }
            else {
                if(i==0) {
                    nums[i] = sum;
                }
                else {
                    nums[i] = sum-1;

                    i--;
                    while(i!=0) {
                        nums[i] = 0;
                        i--;
                    }

                    nums[0] = 1;    // at this point i will be 0
                }
            }
        }
//  converting int array to single int
        return CommonHelper.combineArrayElements(nums);
    }

}
