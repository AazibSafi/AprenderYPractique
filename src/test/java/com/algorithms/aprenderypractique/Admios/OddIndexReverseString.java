package com.algorithms.aprenderypractique.Admios;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/**
 * Reverse the String with only odd indexes
 */
public class OddIndexReverseString extends BaseTest {

    @Test
    public void test() {
        System.out.println(OddIndexReverseString.reverse_odd("hello world"));
    }

    public static String reverse_odd(String sentence) {
        StringBuilder sentenceBuilder = new StringBuilder(sentence);
        int size = sentenceBuilder.length();
        int rightIndex = size%2 == 0 ? size-1 : size-2;

        for(int leftIndex=1; leftIndex<=rightIndex; leftIndex=leftIndex+2, rightIndex=rightIndex-2) {
            swap( sentenceBuilder, leftIndex , rightIndex );
        }

        return sentenceBuilder.toString();
    }

    private static void swap(StringBuilder sentence, int i, int j) {
        Character temp = sentence.charAt(i);
        sentence.setCharAt( i , sentence.charAt(j) );
        sentence.setCharAt( j , temp );
    }

}
