package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string
 * https://www.youtube.com/watch?v=BJGNVQiLNDs&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 */
public class FirstUniqueCharacter extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals(0, firstUniqChar("leetcode"));
        Assert.assertEquals(2, firstUniqChar("loveleetcode"));
        Assert.assertEquals(-1, firstUniqChar("aabb"));
    }

    public int firstUniqChar(String s) {

        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
        }

        for(int i=0; i<s.length(); i++) {
            if(map.get(s.charAt(i)) == 1)
                return i;
        }

        return -1;
    }

}
