package com.algorithms.aprenderypractique.Algorithms.Design.RateLimiter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *  https://leetcode.com/discuss/interview-question/system-design/124558/Uber-or-Rate-Limiter
 *  Solution: https://leetcode.com/discuss/interview-question/system-design/124558/Uber-or-Rate-Limiter/222292
 */
public class RateLimiter_WithRequestLimit {
    private final Map<String, HitCounter> clientTimeStamps;

    public RateLimiter_WithRequestLimit() {
        clientTimeStamps = new HashMap<>();
    }

    public boolean isAllow(String clientId) {
        return clientTimeStamps.computeIfAbsent(clientId, k -> new HitCounter()).hit(now());
    }

    public long now() {
        return System.currentTimeMillis();
    }
}

/**
 *  Same Concept
 *  @see com.algorithms.aprenderypractique.Algorithms.Design.HitCounter.HitCounter_Queue
 */
class HitCounter {
    private final Queue<Long> queue;
    private final int REQUEST_LIMIT = 100;  // 100 Request Allowed per TIME_LIMIT
    private final long TIME_LIMIT = 1000L;  // 1 Sec

    public HitCounter() {
        queue = new LinkedList<>();
    }

    public boolean hit(long timestamp) {
        while(!queue.isEmpty() && timestamp - queue.peek() >= TIME_LIMIT)
            queue.poll();       // Since Timestamp is monotonically increasing, Remove out of window timestamps

        if(queue.size() > REQUEST_LIMIT)
            return false;       // Too many request occurred within time frame

        queue.add(timestamp);
        return true;            // Request passed the condition of timeframe and request limit
    }
}
