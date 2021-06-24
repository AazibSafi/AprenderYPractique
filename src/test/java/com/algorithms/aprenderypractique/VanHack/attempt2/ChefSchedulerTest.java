package com.algorithms.aprenderypractique.VanHack.attempt2;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ChefSchedulerTest extends BaseTest {

    @Test
    public void itShouldWorkOnBasicExamples() {
        HashMap<String, ArrayList<String>> requests = new HashMap<>();
        requests.put(
                "ada", new ArrayList<>(Arrays.asList(
                        new String[]{"mon", "tue", "wed", "fri", "sat", "sun"}
                ))
        );
        requests.put(
                "emma", new ArrayList<>(Arrays.asList(
                        new String[]{"tue", "wed", "thu", "sun"}
                ))
        );
        requests.put(
                "remy", new ArrayList<>(Arrays.asList(
                        new String[]{"mon", "sat"}
                ))
        );
        requests.put(
                "zach", new ArrayList<>(Arrays.asList(new String[]{}))
        );
        assertEquals(
                "it should work on a basic example.",
                true, ChefScheduler.schedulable(requests)
        );

    }

    @Test
    public void notEnoughCoverage() {
        HashMap<String, ArrayList<String>> requests = new HashMap<>();
        requests.put(
                "emma", new ArrayList<>(Arrays.asList(
                        new String[]{"sun"}
                ))
        );
        requests.put(
                "remy", new ArrayList<>(Arrays.asList(
                        new String[]{"sun"}
                ))
        );
        requests.put(
                "zach", new ArrayList<>(Arrays.asList(new String[]{}))
        );
        assertEquals(
                "it should handle an example where there isn't enough coverage on a day.",
                false, ChefScheduler.schedulable(requests)
        );

    }

    @Test
    public void notexcess5Days() {
        HashMap<String, ArrayList<String>> requests = new HashMap<>();
        requests.put(
                "ada", new ArrayList<>(Arrays.asList(
                        new String[] {"mon", "tue", "wed", "thu", "fri", "sat"}
                ))
        );
        requests.put(
                "emma", new ArrayList<>(Arrays.asList(
                        new String[] {"tue", "wed", "thu", "sun"}
                ))
        );
        requests.put(
                "remy", new ArrayList<>(Arrays.asList(
                        new String[] {"mon", "fri", "sun"}
                ))
        );
        requests.put(
                "zach", new ArrayList<>(Arrays.asList(new String[] {}))
        );
        assertEquals(
                "it should handle an example where an employee would need to work in excess of 5 days.",
                false, ChefScheduler.schedulable(requests)
        );
    }

}
