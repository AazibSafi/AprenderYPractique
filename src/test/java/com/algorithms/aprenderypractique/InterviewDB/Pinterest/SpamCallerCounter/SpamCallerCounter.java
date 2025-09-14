package com.algorithms.aprenderypractique.InterviewDB.Pinterest.SpamCallerCounter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Pinterest Problem
public class SpamCallerCounter {

    public Map<String, Integer> findSpammers(List<Call> calls, List<SpamReport> reports) {
        Map<String, Integer> spamCounts = new HashMap<>();

        // Group calls by receiver for faster lookups
        Map<String, List<Call>> receiverToCall = new HashMap<>();
        for(Call call : calls) {
            receiverToCall.computeIfAbsent(call.receiver, k->new ArrayList<>()).add(call);
        }

        // For each spam report, check matching calls
        for(SpamReport spamReport : reports) {
            for(Call call : receiverToCall.get(spamReport.receiver)) {
                if(Math.abs(call.timestamp - spamReport.timestamp) <= 2) {
                    spamCounts.merge(call.caller, 1, Integer::sum);
                }
            }
        }
        return spamCounts;
    }
}

@Data
@AllArgsConstructor
class Call {
    String caller;
    String receiver;
    int timestamp;  // seconds
}

@Data
@AllArgsConstructor
class SpamReport {
    String receiver;
    int timestamp;
}