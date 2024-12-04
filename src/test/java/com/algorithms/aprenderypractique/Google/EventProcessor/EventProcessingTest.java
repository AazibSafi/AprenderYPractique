package com.algorithms.aprenderypractique.Google.EventProcessor;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

/*
    Asked in Google Interview

    Problem Statement;

    Create a System ( API or interface in java ) which deals with the event processing.

    - start(seqId) ->  method will be used for restoring the system if the machine is crashed
    - ack(seqId) -> method will mark the process as done
    - getLowestAck() -> method will return the lowest ack id to be processed next.

    Assuming the event ids are coming from third party.
    And our system will be used in concurrent environment.
 */
public class EventProcessingTest extends BaseTest {

    @Test
    public void test() {
        ConcurrentEventProcessor processor = new ConcurrentEventProcessor();

        processor.start(1); // Starting from seqId 1
        processor.addEvent(1);
        processor.addEvent(2);
        processor.addEvent(3);

        System.out.println("Lowest Ack: " + processor.getLowestAck()); // Output: 1
        processor.ack(1);

        System.out.println("Lowest Ack: " + processor.getLowestAck()); // Output: 2
        processor.ack(2);

        System.out.println("Lowest Ack: " + processor.getLowestAck()); // Output: 3
    }
}
