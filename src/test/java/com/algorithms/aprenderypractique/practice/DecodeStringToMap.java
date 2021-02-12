package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DecodeStringToMap extends BaseTest {

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
        final String input = "=one1";
        Map<String, String> result = decode(input);

        printMap(result);

        Map<String, String> expected = new HashMap<>();
        expected.put("", "one1");
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

    @Test
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
    }

    public Map<String, String> decode2(String str) {
        Map<String, String> map = new HashMap<>();
        return Arrays.asList(str.split("&")).stream()
                .map(s -> s.split("="))
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));
    }

    public Map<String, String> decode3(String s) {
        Map<String, String> map = new HashMap<>();
        if (s == null) {
            map = null;
        } else if (s.isEmpty()) {
            map = new HashMap<>();
        } else if (validateFormat(s)) {
            String[] allValues = s.split("&");
            for (String obj : allValues) {
                String[] keyValue = obj.split("=");
                if (keyValue.length == 1) {
                    map.put(keyValue[0], "");
                } else {
                    map.put(keyValue[0], keyValue[1]);
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid Format");
        }
        return map;
    }

    public Map<String, String> decode(String s) {
        if (s == null) return null;
        Map<String, String> map = new HashMap<>();
        if (s.isEmpty()) return map;
        String regex = "^(?:\\w*=\\w*(?:&|$))+$";
        if (!Pattern.matches(regex, s) || s.charAt(s.length() - 1) == '&') {
            throw new IllegalArgumentException("Invalid Format!!!");
        }
        String[] keyValues = s.split("&");
        for (String pair : keyValues) {
            String[] kv = pair.split("=");
            String key = kv[0];
            String value = "";

            if (kv.length == 2) {

                value = kv[1];
            }

            map.put(key, value);
        }
        return map;
    }

    public boolean validateFormat(String input) {
        return Pattern.compile("^(?:\\w*=\\w*(?:&|$))+$").matcher(input).matches()
                &&
                input.charAt(input.length() - 1) != '&';
    }

}
