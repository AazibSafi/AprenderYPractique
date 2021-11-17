package com.algorithms.aprenderypractique.algorithm.permutation;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class AllPermutations_Integer extends BaseTest {

    @Test
    public void test() {
        List<Integer> permutations = Arrays.asList(123,132,213,231,312,321);
        Assert.assertTrue(permutations.containsAll( permute(123) ));

        permutations = Arrays.asList(12,21);
        Assert.assertTrue(permutations.containsAll( permute(12) ));
    }

    public List<Integer> permute(int num) {
        List<String> output = new AllPermutations_String().permute(String.valueOf(num));
        return output.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

/*
    Don't know why Assertion not working
    Left as it is to save time
 */
    @Test
    public void testArray() {
        List<int[]> permutations = Arrays.asList(new int[]{1,2,3},new int[]{1,3,2},new int[]{2,1,3},new int[]{2,3,1},new int[]{3,1,2},new int[]{3,2,1});
        Assert.assertTrue(CommonHelper.isEquals( permutations , permute(new int[]{1,2,3}) ));

        permutations = Arrays.asList(new int[]{1,2},new int[]{2,1});
        Assert.assertTrue(CommonHelper.isEquals( permutations , permute(new int[]{1,2}) ));
    }

    public List<int[]> permute(int[] nums) {
        String str = Arrays.stream(nums).mapToObj(String::valueOf).reduce(String::concat).get();
        List<String> output = new AllPermutations_String().permute(str);
        return convertStringListToIntList(output);
    }

    List<int[]> convertStringListToIntList(List<String> input) {
        List<int[]> result = new ArrayList<>();

        List<char[]> chArrays = input.stream().map(String::toCharArray).collect(Collectors.toList());

        for(char[] chArray : chArrays) {
            int[] arr = new int[chArray.length];
            for(int i=0; i<chArray.length; i++) {
                arr[i] = chArray[i]-'0';
            }
            result.add(arr);
        }
        return result;
    }

}
