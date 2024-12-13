package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=223547538732703
 */
public class EncryptedStrings extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("bac", findEncryptedWord("abc"));
        Assert.assertEquals("bacd", findEncryptedWord("abcd"));
        Assert.assertEquals("xbacbca", findEncryptedWord("abcxcba"));
        Assert.assertEquals("eafcobok", findEncryptedWord("facebook"));
    }

    public String findEncryptedWord(String s) {
        StringBuilder encrypted = new StringBuilder();
        encrypt(s, encrypted,0, s.length()-1);
        return encrypted.toString();
    }

/*
    Probably Time: O(N)
    because each char is visited from the string single time.
 */
    private void encrypt(String s, StringBuilder encrypted, int start, int end) {
        if(start > end) return;

        int mid = (start+end)/2;

        encrypted.append(s.charAt(mid));
        encrypt(s,encrypted,start,mid-1);
        encrypt(s,encrypted,mid+1,end);
    }

}
