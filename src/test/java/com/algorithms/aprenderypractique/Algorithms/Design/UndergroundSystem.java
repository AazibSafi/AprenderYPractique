package com.algorithms.aprenderypractique.Algorithms.Design;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/design-underground-system
 *      https://leetcode.com/problems/design-underground-system/solutions/3580382/python-java-c-simple-solution-easy-to-understand
 *
 *      Space complexity : O(P + S^2)
 */
public class UndergroundSystem extends BaseTest {

    @Test
    public void test() {
        double epsilon = 0.000001d;
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        Assert.assertTrue(undergroundSystem.getAverageTime("Paradise", "Cambridge") - 14.00 < epsilon);
        undergroundSystem.checkIn(10, "Leyton", 24);
        Assert.assertTrue(undergroundSystem.getAverageTime("Leyton", "Waterloo") - 11.00 < epsilon);
        undergroundSystem.checkIn(10, "Leyton", 24);
        Assert.assertTrue(undergroundSystem.getAverageTime("Leyton", "Waterloo") - 11.00 < epsilon);
        undergroundSystem.checkOut(10, "Waterloo", 38);
        Assert.assertTrue(undergroundSystem.getAverageTime("Leyton", "Waterloo") - 12.00 < epsilon);
        Assert.assertEquals(2, undergroundSystem.getTotalNumberOfTrips(10));
        undergroundSystem.checkIn(32, "Toronto", 8);
        undergroundSystem.checkIn(27, "New York", 10);
        undergroundSystem.checkOut(32, "New York", 20);
        undergroundSystem.checkOut(27, "Toronto", 35);
        undergroundSystem.checkIn(10, "Montreal", 15);
        undergroundSystem.checkOut(10, "Ottawa", 25);
        Assert.assertEquals(2, undergroundSystem.getTotalNumberOfTrips(32));
        Assert.assertEquals(2, undergroundSystem.getTotalNumberOfTrips(27));
        Assert.assertEquals(3, undergroundSystem.getTotalNumberOfTrips(10));
    }

    Map<Integer, CheckInInfo> checkIns;
    Map<String, TravelInfo> trips;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        trips = new HashMap<>();
    }

    // O(1) -> Inserting a key/value pair into a HashMap
    public void checkIn(int id, String stationName, int t) {
        // To count the Total Number Of Trips with passenger :id    // Ref: getTotalNumberOfTrips
        int numberOfCheckIns = 1;
        if(checkIns.containsKey(id))
            numberOfCheckIns += checkIns.get(id).numberOfCheckins;

        checkIns.put(id, new CheckInInfo(stationName, t, numberOfCheckIns));
    }

    // O(1) -> Getting the corresponding value for a key from a HashMap
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo checkInInfo = checkIns.get(id);
        String travelStation = getStationKey(checkInInfo.stationName, stationName);
        int tripTime = t - checkInInfo.time;
        trips.computeIfAbsent(travelStation, k -> new TravelInfo()).addTrip(tripTime);
    }

    // Dividing two numbers
    public double getAverageTime(String startStation, String endStation) {
        TravelInfo travelInfo = trips.get(getStationKey(startStation, endStation));
        return (1.0*travelInfo.totalTime)/travelInfo.totalTrips;
    }

    /*
        - Additional Method to get the total number of trips made a passenger.
        - Just added new field numberOfCheckin in CheckInInfo
        - O(1)
    */
    public int getTotalNumberOfTrips(int id) {
        return checkIns.get(id).numberOfCheckins;
    }

    public String getStationKey(String startStation, String endStation) {
        return startStation + "->" + endStation;
    }

    class TravelInfo {
        int totalTime;
        int totalTrips;
        public TravelInfo() {
            this.totalTime = 0;
            this.totalTrips = 0;
        }
        public void addTrip(int totalTime) {
            this.totalTime += totalTime;
            this.totalTrips += 1;
        }
    }

    class CheckInInfo {
        String stationName;
        int time;
        int numberOfCheckins;   // Ref: getTotalNumberOfTrips
        public CheckInInfo(String stationName, int time, int numberOfCheckins) {
            this.stationName = stationName;
            this.time = time;
            this.numberOfCheckins += numberOfCheckins;
        }
    }
}