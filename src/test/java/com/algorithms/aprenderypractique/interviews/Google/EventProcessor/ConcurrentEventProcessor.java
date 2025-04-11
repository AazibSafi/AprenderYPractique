package com.algorithms.aprenderypractique.interviews.Google.EventProcessor;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentEventProcessor implements EventProcessor {

    // Maintains the order of event sequence IDs.
    // Efficient for concurrent access and retrieval of the smallest element.
    private final ConcurrentSkipListSet<Long> unacknowledgedEvents = new ConcurrentSkipListSet<>();

    // Ensures the last processed sequence ID is updated atomically in a concurrent environment.
    private final AtomicLong lastProcessedSeqId = new AtomicLong(-1);

    @Override
    public void start(long seqId) {
        lastProcessedSeqId.set(seqId - 1); // Assuming seqId is the first unprocessed event
        unacknowledgedEvents.clear();
    }

    @Override
    public void ack(long seqId) {
        unacknowledgedEvents.remove(seqId);
        lastProcessedSeqId.updateAndGet(prev -> Math.max(prev, seqId));
    }

    @Override
    public long getLowestAck() {
        return unacknowledgedEvents.isEmpty() ? lastProcessedSeqId.get() + 1 : unacknowledgedEvents.first();
    }

    /**
     * Simulates the arrival of an event to the system.
     * This method would typically be called by the system receiving events from a third party.
     *
     * @param seqId The sequence ID of the incoming event.
     */
    public void addEvent(long seqId) {
        if (seqId > lastProcessedSeqId.get()) {
            unacknowledgedEvents.add(seqId);
        }
    }
}
