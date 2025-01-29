package com.algorithms.aprenderypractique.Algorithms.Design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/design-parking-system
 *
 *      Follow-up questions is type is not constrain in parking
 *          2 small can park in one medium
 *          1 big and park in 4 small
 */
// Approach#2
public class ParkingSystem {
    int[] parkingLot;

    public ParkingSystem(int big, int medium, int small) {
        parkingLot = new int[]{big, medium, small};
    }

    public boolean addCar2(int carType) {
        if(parkingLot[carType - 1] > 0) {
            parkingLot[carType - 1]--;
            return true;
        }
        return false;
    }

    public boolean addCar(int carType) {
        return parkingLot[carType - 1]-- > 0;
    }
}

// Approach#1
class ParkingSystem1 {
    enum CarType {
        BIG, MEDIUM, SMALL;
        static CarType valueOf(int val) {
            return Arrays.stream(CarType.values())
                    .filter(type -> type.ordinal() == val)
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    Map<CarType, Integer> parkingLot;

    public ParkingSystem1(int big, int medium, int small) {
        parkingLot = new HashMap<>();
        parkingLot.put(CarType.BIG, big);
        parkingLot.put(CarType.MEDIUM, medium);
        parkingLot.put(CarType.SMALL, small);
    }

    public boolean addCar(int carType) {
        CarType type = CarType.valueOf(carType-1);
        if(parkingLot.get(type) > 0) {
            parkingLot.put(type, parkingLot.get(type)-1);
            return true;
        }
        return false;
    }
}
