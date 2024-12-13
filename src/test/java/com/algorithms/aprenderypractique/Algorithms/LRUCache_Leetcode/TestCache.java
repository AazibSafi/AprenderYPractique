package com.algorithms.aprenderypractique.Algorithms.LRUCache_Leetcode;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Cache.Cache;
import com.algorithms.aprenderypractique.Cache.Policy.EvictionPolicy;
import org.junit.Assert;
import org.junit.Test;

/**
 *  This Test the Cache Package
 *  @see Cache
 */
public class TestCache extends BaseTest {

    @Test
    public void testLRU() {
        Cache<Integer, Integer> cache = new Cache<>(3, EvictionPolicy.LRU);
        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(Integer.valueOf(1), cache.get(1));

        cache.put(3, 3);
        cache.put(5, 5);
        Assert.assertNull(cache.get(2));

        cache = new Cache<>(2, EvictionPolicy.LRU);
        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(Integer.valueOf(1), cache.get(1));

        cache.put(3, 3);
        Assert.assertNull(cache.get(2));

        cache.put(4, 4);
        Assert.assertNull(cache.get(1));
        Assert.assertEquals(Integer.valueOf(3), cache.get(3));
        Assert.assertEquals(Integer.valueOf(4), cache.get(4));
    }

    @Test
    public void testLRU_string() {
        Cache<Character, Character> cache = new Cache<>(3, EvictionPolicy.LRU);
        cache.put('a', 'a');
        cache.put('b', 'b');
        Assert.assertEquals(Character.valueOf('a'), cache.get('a'));

        cache.put('c', 'c');
        cache.put('d', 'd');
        Assert.assertNull(cache.get('b'));

        cache = new Cache<>(2, EvictionPolicy.LRU);
        cache.put('a', 'a');
        cache.put('b', 'b');
        Assert.assertEquals(Character.valueOf('a'), cache.get('a'));

        cache.put('c', 'c');
        Assert.assertNull(cache.get('b'));

        cache.put('d', 'd');
        Assert.assertNull(cache.get('a'));
        Assert.assertEquals(Character.valueOf('c'), cache.get('c'));
        Assert.assertEquals(Character.valueOf('d'), cache.get('d'));
    }

    @Test
    public void testMRU() {
        // Todo
    }

}
