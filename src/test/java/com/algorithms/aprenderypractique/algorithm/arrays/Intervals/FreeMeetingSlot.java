package com.algorithms.aprenderypractique.algorithm.arrays.Intervals;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *  Given the busy slots of each employees with start and end time
 *  find the common free slots for everyone to schedule a new meeting
 * https://www.youtube.com/watch?v=tafUkDPWIbM
 */
public class FreeMeetingSlot extends BaseTest {

    @Test
    public void solution() {
        int[][] busySlots = new int[][]{{1,2},{1,3},{5,6},{4,10}};  //  {{1,3},{4,10}}; // {1,2},{1,3},{4,10},{5,6}
        List<Integer[]> commonAvailableSlots = findAvailableSlots(busySlots,1);

        commonAvailableSlots.forEach( slot -> System.out.println("{" + slot[0] + "," + slot[1] + "}"));  //   { 3 , 4 }
    }

/*
    Note: Assume the slots are in sorted form, otherwise busySlots needs to be sorted first
    check if the busy slot endTime is less than the start time of the next busySlot,
    and if the diff btw the start time of the next busySlot and endTime of current busy slot is greater or equal to newMeetingDuration
    Then we found the free slot

    O(N), where N is busySlot intervals
    -- Doubt in complexity. bcz Sorting itself is nLogN so overall complexity cannot be O(N)
 */
    public List<Integer[]> findAvailableSlots(int[][] busySlots, int newMeetingDuration) {

        List<Integer[]> commonAvailableSlots = new ArrayList<>();
        int startTime=0, endTime=1;

        Arrays.sort(busySlots, Comparator.comparingInt(o -> o[startTime]));

        for(int row=0; row<busySlots.length-1; row++) {
            if( //busySlots[row+1][startTime] > busySlots[row][endTime] &&
                busySlots[row+1][startTime] - busySlots[row][endTime] >= newMeetingDuration ) {

                Integer[] availableSlot = new Integer[] {
                        busySlots[row][endTime],
                        busySlots[row+1][startTime] };

                commonAvailableSlots.add(availableSlot);
            }
        }

        return commonAvailableSlots;
    }

}
