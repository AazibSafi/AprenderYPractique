package com.algorithms.aprenderypractique.InterviewDB.Pinterest.MaxVisiblePins;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
    Sliding Window
    Time: O(N * R)
    Space: O(1)

    Let:
        N = number of pins
        R = maxBottom - minTop = vertical scroll range

    Pinterest Problem
 */
public class MaxVisiblePins_SlidingWindow {

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

        int maxPins = 0;

        // Find global min and max bounds
        int minTop = pins.stream().mapToInt(pin -> pin.top).min().getAsInt();
        int maxBottom = pins.stream().mapToInt(pin -> pin.bottom).max().getAsInt();

        // Try every possible sliding window of length screenLen
        for(int start = minTop; start <= maxBottom; start++) {
            int count = 0;
            int end = start + screenLen;
            for (Pin pin : pins) {
                if(pin.top >= start && pin.bottom <= end) {
                    count++;
                }
            }
            maxPins = Math.max(maxPins, count);
        }
        return maxPins;
    }

    @Test
    public void testAllCases() {
        // Case 1: Basic example from problem statement
        List<Pin> pins1 = Arrays.asList(
                new Pin(1, 4, "L"),
                new Pin(2, 3, "R"),
                new Pin(4, 8, "R"),
                new Pin(6, 9, "L")
        );
        Assert.assertEquals(2, getMaxPins(pins1, 5));

        // Case 2: Single pin fits fully inside screen
        List<Pin> pins2 = Collections.singletonList(new Pin(0, 3, "L"));
        Assert.assertEquals(1, getMaxPins(pins2, 5));

        // Case 3: Single pin taller than screen → cannot fit
        List<Pin> pins3 = Collections.singletonList(new Pin(0, 10, "R"));
        Assert.assertEquals(0, getMaxPins(pins3, 5));

        // Case 4: All pins fit inside one screen window
        List<Pin> pins4 = Arrays.asList(
                new Pin(0, 2, "L"),
                new Pin(2, 4, "R"),
                new Pin(4, 6, "L")
        );
        Assert.assertEquals(3, getMaxPins(pins4, 7));

        // Case 5: Pins are scattered → only one visible at a time
        List<Pin> pins5 = Arrays.asList(
                new Pin(0, 2, "L"),
                new Pin(10, 12, "R"),
                new Pin(20, 22, "L")
        );
        Assert.assertEquals(1, getMaxPins(pins5, 5));

        // Case 6: Empty pin list → no visible pins
        List<Pin> pins6 = new ArrayList<>();
        Assert.assertEquals(0, getMaxPins(pins6, 5));

        // Case 7: Exact fit at boundary → both pins fit
        List<Pin> pins7 = Arrays.asList(
                new Pin(5, 8, "L"),
                new Pin(8, 11, "R")
        );
        Assert.assertEquals(2, getMaxPins(pins7, 6));
    }

}
