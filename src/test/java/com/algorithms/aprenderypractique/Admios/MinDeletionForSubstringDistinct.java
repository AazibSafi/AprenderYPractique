package com.algorithms.aprenderypractique.Admios;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 *  Reduce the string by deleting any characters to make all substring distinct
 *  Return: number of minimum deletion
 */
public class MinDeletionForSubstringDistinct extends BaseTest {

    @Test
    public void test() {
        System.out.println(MinDeletionForSubstringDistinct.getMinDeletions("ababa"));
    }

    public static int getMinDeletions(String s) {
        int maxLength = 100000;

        int distinct = 0;
        int[] count = new int[maxLength];

        for(int i=0; i<maxLength; i++) {
            count[i] = 0;
        }

        for(int i=0; i<s.length(); i++) {
            if(count[s.charAt(i)-'a']==0) {
                distinct++;
            }
            count[s.charAt(i)-'a']++;
        }

        return s.length() - distinct;
    }

}
