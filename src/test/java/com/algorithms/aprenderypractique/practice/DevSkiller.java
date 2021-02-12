package com.algorithms.aprenderypractique.practice;

import com.google.common.base.Splitter;
import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DevSkiller extends BaseTest {

    public void printMap(Map<String, String> mp) {
        if (mp != null) {
            for (Map.Entry<String, String> entry : mp.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    @Test
    public void test1() {
        final String input = "one=1&two=2";
        Map<String, String> result = decode(input);

        printMap(result);

        Map<String, String> expected = new HashMap<>();
        expected.put("one", "1");
        expected.put("two", "2");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test2() {
        final String input = "";
        Map<String, String> result = decode(input);

        printMap(result);

        Map<String, String> expected = new HashMap<>();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test3() {
        final String input = null;
        Map<String, String> result = decode(input);

        printMap(result);
        Assert.assertEquals(null, result);
    }

    @Test
    public void test4() {
        final String input = "one=1&two=";
        Map<String, String> result = decode(input);

        printMap(result);

        Map<String, String> expected = new HashMap<>();
        expected.put("one", "1");
        expected.put("two", "");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test5() {
        final String input = "one=1&=2";
        Map<String, String> result = decode(input);

        printMap(result);

        Map<String, String> expected = new HashMap<>();
        expected.put("one", "1");
        expected.put("", "2");
        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test6() {
        final String input = "one=1&";
        Map<String, String> result = decode(input);
        printMap(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test7() {
        final String input = "&one=1";
        Map<String, String> mp = decode(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test8() {
        final String input = "one&=1";
        Map<String, String> mp = decode(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test9() {
        final String input = "one1";
        Map<String, String> mp = decode(input);
    }

    @Test
    public void test10() {
        final String input = "=1";
        Map<String, String> result = decode(input);

        printMap(result);

        Map<String, String> expected = new HashMap<>();
        expected.put("", "1");
        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test11() {
        final String input = "&one1";
        Map<String, String> mp = decode(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test12() {
        final String input = "==one1";
        Map<String, String> mp = decode(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test13() {
        final String input = "one=1&two=2&one=1";
        Map<String, String> result = decode(input);

        printMap(result);

        Map<String, String> expected = new HashMap<>();
        expected.put("one", "1");
        expected.put("two", "2");
        expected.put("one", "1");
        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test14() {
        final String input = "one=1&=two=2";
        Map<String, String> mp = decode(input);
        if (mp != null) {
            for (Map.Entry<String, String> entry : mp.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    /*
        An IllegalArgumentException is thrown If the given String has an invalid format.
     */
    public Map<String, String> decode(String s) {
        Map<String, String> map;
        if (s == null) {
            map = null;
        } else if (s.isEmpty()) {
            map = new HashMap<>();
        } else {
            map = Splitter.on('&').withKeyValueSeparator('=').split(s);
        }
        return map;
    }

}
