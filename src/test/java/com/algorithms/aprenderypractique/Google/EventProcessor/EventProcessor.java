package com.algorithms.aprenderypractique.Google.EventProcessor;

public interface EventProcessor {
    /**
     * Starts the processing of events, restoring the state of the system
     * if the machine has crashed.
     *
     * Initializes or restores the system state starting from a specific sequence ID.
     *
     * @param seqId The starting sequence ID to restore from.
     */
    void start(long seqId);

    /**
     * Marks the event with the given sequence ID as processed.
     * Acknowledges processing of a specific sequence ID, removes it from the unacknowledged set, and updates the last processed ID.
     *
     * @param seqId The sequence ID of the event to acknowledge.
     */
    void ack(long seqId);

    /**
     * Gets the lowest sequence ID that has not yet been acknowledged.
     * Returns the next unprocessed event, or the next expected sequence ID if all previous events are acknowledged.
     *
     * @return The lowest unacknowledged sequence ID.
     */
    long getLowestAck();
}
