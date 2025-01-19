package com.algorithms.aprenderypractique.Algorithms.Design.RateLimiter;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/logger-rate-limiter
 *      Time: O(1) --> The lookup and update of the hashtable takes a constant time.
 *      Space: O(M) --> where M is the size of all incoming messages. Over the time, the hashtable would have an entry for each unique message that has appeared.
 *
 *      Similar:
 *          @see RateLimiter
 *          @see com.algorithms.aprenderypractique.Algorithms.Design.HitCounter.HitCounter_Queue
 */
public class Logger {
    private final Map<String, Integer> logs;
    private final int TIMEOUT;

    public Logger() {
        logs = new HashMap<>();
        TIMEOUT = 10;
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if(logs.containsKey(message)
                && (timestamp - logs.get(message)) < TIMEOUT) {
            return false;
        }
        logs.put(message, timestamp);
        return true;
    }
}
