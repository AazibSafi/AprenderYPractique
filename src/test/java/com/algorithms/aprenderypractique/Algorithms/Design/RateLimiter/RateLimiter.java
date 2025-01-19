package com.algorithms.aprenderypractique.Algorithms.Design.RateLimiter;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://hackerranksolution.in/ratelimiteramazon
 *      https://leetcode.com/discuss/interview-question/system-design/124558/Uber-or-Rate-Limiter
 *      Time: O(1) --> The lookup and update of the hashtable takes a constant time.
 *      Space: O(M) --> where M is the size of all incoming messages. Over the time, the hashtable would have an entry for each unique message that has appeared.
 *
 *      Similar:
 *          @see Logger
 *          @see com.algorithms.aprenderypractique.Algorithms.Design.HitCounter.HitCounter_Queue
 */
public class RateLimiter {
    private final Map<Integer, Integer> requests;    //  <User uid, timestamp>
    private final int expire;

    public RateLimiter(int expire) {
        requests = new HashMap<>();
        this.expire = expire;
    }

    public boolean limit(int uid, int timestamp) {
        if(requests.containsKey(uid) && timestamp - requests.get(uid) <= expire)
            return true;    // User Request's Failed. Do Limit the user incoming Request
        else {
            requests.put(uid, timestamp);
            return false;   // User Request's Passed. Let the incoming Request work
        }
    }

}
