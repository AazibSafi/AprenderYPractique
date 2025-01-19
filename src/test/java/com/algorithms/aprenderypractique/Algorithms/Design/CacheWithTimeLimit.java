package com.algorithms.aprenderypractique.Algorithms.Design;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.util.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *      https://leetcode.com/problems/cache-with-time-limit
 *      https://algo.monster/liteproblems/2622
 */
public class CacheWithTimeLimit extends BaseTest {

    @Test
    public void test() throws InterruptedException {
        CacheWithTimeLimit myCache = new CacheWithTimeLimit();
        Assert.assertFalse(myCache.setValue(1, 42, 1000));
        Assert.assertEquals(42, myCache.getValue(1));
        Assert.assertEquals(1, myCache.countCacheEntries());
        Thread.sleep(6000);
        Assert.assertEquals(-1, myCache.getValue(1));
        Assert.assertEquals(0, myCache.countCacheEntries());
        Assert.assertFalse(myCache.setValue(1, 84, 5));
        Assert.assertEquals(84, myCache.getValue(1));
    }

    private final Map<Integer, Pair<Integer, Long>> cache;     // <Key, {value, expirationTime}>

    public CacheWithTimeLimit() {
        cache = new ConcurrentHashMap<>();
    }

    public boolean setValue(int key, int value, int duration) {
        boolean alreadyExist = cache.containsKey(key);
        cache.put(key, Pair.of(value, now() + duration));
        return alreadyExist;
    }

    public int getValue(int key) {
        removeExpired();
        return cache.containsKey(key) ? cache.get(key).getFirst() : -1;
    }

    public int countCacheEntries() {
        removeExpired();
        return cache.size();
    }

    public void removeExpired() {
        cache.forEach((key,val) -> {
            if(val.getSecond() < now())
                cache.remove(key);
        });
    }

    public long now() {
        return System.currentTimeMillis();
    }

}
