package com.algorithms.aprenderypractique.InterviewDB.Pinterest.PinViolationAnalysis;

import java.util.*;

/*
    Pinterest Problem
 */
public class PinViolationAnalysis {

    Map<String, Set<String>> pinToPolicies;
    Map<String, Set<String>> policyToPins;
    TreeMap<String, Set<String>> dateToPins;

    PinViolationAnalysis(List<String[]> logs) {
        pinToPolicies = new HashMap<>();
        policyToPins = new HashMap<>();
        dateToPins = new TreeMap<>();

        for(String[] log : logs) {
            String pinId = log[0];
            String policyId = log[1];
            String date = log[2];

            pinToPolicies.computeIfAbsent(pinId, k -> new HashSet<>()).add(policyId);
            policyToPins.computeIfAbsent(policyId, k -> new HashSet<>()).add(pinId);
            dateToPins.computeIfAbsent(date, k -> new HashSet<>()).add(pinId);
        }
    }

    // Task 1: Find Pins Violating Specific Policy in O(1)
    public Set<String> findPinsByPolicy(String policy) {
        return policyToPins.getOrDefault(policy, new HashSet<>());
    }

    // Task 2: Find Policies Violated by Pin in O(1)
    public Set<String> findPolicesByPinId(String pinId) {
        return pinToPolicies.getOrDefault(pinId, new HashSet<>());
    }

    // Task 3: Find All Violations on Specific Date in O(1)
    public Set<String> findViolationsByDate(String date) {
        return dateToPins.getOrDefault(date, new HashSet<>());
    }

    // Task 4: Find Violations in a Date Range in O(log N)
    public List<String> findViolationsByDateRange(String startDate, String endDate) {
        List<String> result = new ArrayList<>();
        NavigableMap<String, Set<String>> map = dateToPins.subMap(startDate, true, endDate, true);

        for(Set<String> pins : map.values()) {
            result.addAll(new ArrayList<>(pins));
        }
        return result;
    }

}
