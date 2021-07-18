package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class SumOfTwoIntegerInArray extends BaseTest {

        @Test
        public void test() {
            int[] arr = new int[]{5,7,1,2,8,4,3};
            Map<Integer,Integer> result = findSumOfTwoIntegersInArray(arr,10);
            CommonHelper.printMap(result);

            result = findSumOfTwoIntegersInArray(arr,19);
            if(result.isEmpty()) {
                System.out.println("No 2 values sum up to " + 19);
            }
            else {
                CommonHelper.printMap(result);
            }
        }

        public Map<Integer,Integer> findSumOfTwoIntegersInArray(int[] arr, int val) {
            Map<Integer,Integer>  result = new HashMap<>();
            Map<Integer,Integer>  tableCheck = new HashMap<>();

            for(int x : arr) {
                Integer remaining = val - x;
                if(tableCheck.containsKey(x)) {
                    result.put(remaining,x);
                }
                else {
                    tableCheck.put(remaining,x);
                }
            }

            return result;
        }
}
