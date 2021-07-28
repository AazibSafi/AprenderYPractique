package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.assertj.core.internal.Integers;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class RotationalCipher extends BaseTest {

    @Test
    public void test() {
        String str = "Zebra-493?";
        Assert.assertEquals("Cheud-726?",rotationalCipher(str,3));

        str = "abcdefghijklmNOPQRSTUVWXYZ0123456789";
        Assert.assertEquals("nopqrstuvwxyzABCDEFGHIJKLM9012345678",rotationalCipher(str,39));
    }

    public String rotationalCipher(String input, int rotationFactor) {
        StringBuilder encrypt = new StringBuilder();

        for(char c : input.toCharArray()) {
            if(c >= '0' && c <= '9') {
                c = (char) ( ( (Integer.parseInt(c+"") + rotationFactor) % 10 ) + '0');
            }
            else if(c >= 'a' && c <= 'z') {
                c = (char) ( ( c + rotationFactor - 'a' ) % 26 + 'a');
            }
            else if(c >= 'A' && c <= 'Z') {
                c = (char) ( ( c + rotationFactor - 'A' ) % 26 + 'A');
            }

            encrypt.append(c);
        }

        return encrypt.toString();
    }

}
