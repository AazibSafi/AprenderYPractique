package com.algorithms.aprenderypractique.Admios;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/*
 *  Aazib Safi
 * Write a function that capitalizes a text. Example: "this is not capitalized" -> "This Is Not Capitalized"
 *
 * a = 97
 * A = 65
 * 97 - 65 = 32
 */
public class CapitalizeWords extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("This Not Is Not Capitalized",capitalize("this not is not capitalized"));
    }

    public String capitalize(String str) {
        StringBuilder capitalizeSentence = new StringBuilder();

        String[] words = str.split(" ");

        for(String word : words) {
            StringBuilder wordBuilder = new StringBuilder(word);
            wordBuilder.setCharAt(0, (char) (wordBuilder.charAt(0)-32));
            capitalizeSentence.append(wordBuilder);
            capitalizeSentence.append(" ");
        }

        capitalizeSentence = capitalizeSentence.deleteCharAt(capitalizeSentence.length()-1);
        return capitalizeSentence.toString();
    }

}
