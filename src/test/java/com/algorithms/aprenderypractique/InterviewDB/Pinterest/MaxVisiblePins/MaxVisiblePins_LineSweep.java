package com.algorithms.aprenderypractique.InterviewDB.Pinterest.MaxVisiblePins;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
    Line Sweep - Efficient
    Time: O(N * LogN)
    Space: O(N)

    Let:
        N = number of pins
        R = maxBottom - minTop = vertical scroll range

    Pinterest Problem
 */
public class MaxVisiblePins_LineSweep {

    class Pin {
        int top, bottom;
        String col;
        Pin(int t, int b, String c) {
            this.top = t;
            this.bottom = b;
            this.col = c;
        }
    }

    public int getMaxPins(List<Pin> pins, int screenLen) {
        if (pins.isEmpty()) return 0;

        List<int[]> events = new ArrayList<>();

        for(Pin pin : pins) {   // O(N)
            int earliestStart = pin.bottom - screenLen; // must be at least this high to include bottom
            int latestStart   = pin.top;                // must be at most this to include top

            if(earliestStart <= latestStart) {      // only valid if interval makes sense
                events.add(new int[]{earliestStart, 1});    // +1 when entering a valid region
                events.add(new int[]{latestStart+1, -1});   // -1 when leaving the valid region (exclusive end)
            }
        }

        events.sort((e1, e2) -> e1[0]==e2[0] ? e1[1]-e2[1] : e1[0]-e2[0]);  // O(NLogN)

        // Sweep through events to find max overlap
        int maxPins = 0, curr = 0;
        for(int[] event : events) {     // O(N)
            curr += event[1];
            maxPins = Math.max(maxPins, curr);
        }
        return maxPins;
    }

    @Test
    public void testAllCases() {
        // Case 1: Basic example from problem statement
        List<Pin> pins = Arrays.asList(
                new Pin(1, 4, "L"),
                new Pin(2, 3, "R"),
                new Pin(4, 8, "R"),
                new Pin(6, 9, "L")
        );
        Assert.assertEquals(2, getMaxPins(pins, 5));

        // Case 2: Single pin fits fully inside screen
        pins = Collections.singletonList(new Pin(0, 3, "L"));
        Assert.assertEquals(1, getMaxPins(pins, 5));

        // Case 3: Single pin taller than screen → cannot fit
        pins = Collections.singletonList(new Pin(0, 10, "R"));
        Assert.assertEquals(0, getMaxPins(pins, 5));

        // Case 4: All pins fit inside one screen window
        pins = Arrays.asList(
                new Pin(0, 2, "L"),
                new Pin(2, 4, "R"),
                new Pin(4, 6, "L")
        );
        Assert.assertEquals(3, getMaxPins(pins, 7));

        // Case 5: Pins are scattered → only one visible at a time
        pins = Arrays.asList(
                new Pin(0, 2, "L"),
                new Pin(10, 12, "R"),
                new Pin(20, 22, "L")
        );
        Assert.assertEquals(1, getMaxPins(pins, 5));

        // Case 6: Empty pin list → no visible pins
        pins = new ArrayList<>();
        Assert.assertEquals(0, getMaxPins(pins, 5));

        // Case 7: Exact fit at boundary → both pins fit
        pins = Arrays.asList(
                new Pin(5, 8, "L"),
                new Pin(8, 11, "R")
        );
        Assert.assertEquals(2, getMaxPins(pins, 6));
    }

}
