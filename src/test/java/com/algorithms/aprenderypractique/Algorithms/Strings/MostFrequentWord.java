package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MostFrequentWord extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("geeks", mostFrequentWord(new String[]{"geeks", "for", "geeks", "a",  "portal", "to",
                "learn", "can", "be", "computer", "science", "zoom",
                "yup", "fire", "in", "be", "data", "geeks"}));
        Assert.assertEquals("Loblaws", mostFrequentWord(
                new String[]{"Loblaws", "for", "Google",  "Meta", "Loblaws", "learn", "Loblaws"}));
    }

    public String mostFrequentWord(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        return Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

}
