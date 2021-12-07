package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/problems/reverse-vowels-of-a-string/
 *  https://www.youtube.com/watch?v=WAo0mWvHrp8&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=4
 *
 *  Time Complexity: O(N)
 *  Space Complexity: O(N)
 */
public class ReverseVowelsInString extends BaseTest {

    @Test
    public void test() {
        String str = "applepie";
        Assert.assertEquals("epplipea",reverseVowelsInString(str));

        str = "hello";
        Assert.assertEquals("holle",reverseVowelsInString(str));

        str = "leetcode";
        Assert.assertEquals("leotcede",reverseVowelsInString(str));
    }

    public String reverseVowelsInString(String str) {
        char[] charArray = str.toCharArray();

        List<Character> vowels = Arrays.asList('A','E','I','O','U','a','e','i','o','u');

        int i=0, j=charArray.length-1;

        while(i<j) {

            if(!vowels.contains(charArray[i])) {
                i++;
            }

            if(!vowels.contains(charArray[j])) {
                j--;
            }

            if(vowels.contains(charArray[i]) && vowels.contains(charArray[j])) {
                swap(charArray,i,j);
                i++; j--;
            }

        }

        return new String(charArray);
    }

    private void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

}
