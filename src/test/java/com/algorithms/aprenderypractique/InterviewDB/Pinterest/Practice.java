package com.algorithms.aprenderypractique.InterviewDB.Pinterest;

import com.algorithms.aprenderypractique.InterviewDB.Pinterest.MaxVisiblePins.MaxVisiblePins_LineSweep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice {

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

        List<int[]> events = new ArrayList<>();
        for(Pin pin : pins) {
            int earliest = pin.top;
            int latest = pin.bottom - screenLen;
            events.add(new int[]{earliest, 1});
            events.add(new int[]{latest+1, -1});
        }

    }

}
