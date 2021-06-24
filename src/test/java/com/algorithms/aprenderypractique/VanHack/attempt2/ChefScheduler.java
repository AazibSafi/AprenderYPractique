package com.algorithms.aprenderypractique.VanHack.attempt2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
* Each Chef has given with their days off / Leaves
* Restaurant is open 7 days in a week
* There are 2 shifts in a day of 8 hours each
* No Chef should work more than 5 days in a week
* This program checks with given chefs days off, if it is possible to schedule their duties such that all conditions are fulfilled.
 */
public class ChefScheduler {

    public static List<String> daysOfWeek;

    public static void initializeDaysOfWeek() {
        daysOfWeek = Arrays.asList("mon","tue","wed","thu","fri","sat","sun");
    }

    public static boolean schedulable(HashMap<String, ArrayList<String>> requests) {

        initializeDaysOfWeek();
        Map<String,Integer> workingDaysCount = new HashMap<>();
        int MAX_WORKING_DAYS = 5;

        Map<String, List<String>> requestWithAvailableDays = getAvailableDays(requests);

        for( String workingDay : daysOfWeek ) {
            int shift = 0;

            Map<String, List<String>> availableChefsOfTheDay = findAvailableChefsForGivenDay(requestWithAvailableDays,workingDay);

            availableChefsOfTheDay = sortByDaysLength(availableChefsOfTheDay);

            for(Map.Entry<String, List<String>> chef : availableChefsOfTheDay.entrySet()) {
                String chefName = chef.getKey();

                if( workingDaysCount.getOrDefault(chefName,0) + 1 <= MAX_WORKING_DAYS ) {
                    workingDaysCount.put( chefName , workingDaysCount.getOrDefault(chefName,0) + 1 );
                    shift++;
                }

                if(shift>=2) {
                    break;
                }
            }

            if(shift!=2) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, List<String>> findAvailableChefsForGivenDay(Map<String, List<String>> requestWithAvailableDays, String workingDay) {
        return requestWithAvailableDays.entrySet().stream()
                .filter(i -> i.getValue().stream().anyMatch(workingDay::equals))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, List<String>> sortByDaysLength(Map<String, List<String>> unsortMap) {

        List<Map.Entry<String, List<String>>> list = new LinkedList<>(unsortMap.entrySet());
        list.sort(Comparator.comparingInt(o -> o.getValue().size()));

        Map<String, List<String>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static Map<String, List<String>> getAvailableDays(HashMap<String, ArrayList<String>> requests) {
        Map<String, List<String>> availableDaysRequest = new HashMap<>();

        requests.forEach((chef, daysOff) -> {
            List<String> availableDays = daysOfWeek.stream()
                    .filter(dayOfWeek -> !daysOff.contains(dayOfWeek))
                    .collect(Collectors.toList());
            availableDaysRequest.put(chef,availableDays);
        });

        return availableDaysRequest;
    }

}
