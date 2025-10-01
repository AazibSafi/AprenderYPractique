package com.algorithms.aprenderypractique.InterviewDB.Pinterest.Elevator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

// Pinterest.md Problem
public class ElevatorProblem {

    @AllArgsConstructor
    enum Direction {
        IDLE("idle"),
        UP("up"),
        DOWN("down");

        public String dir;
    }

    @Data
    @AllArgsConstructor
    class Elevator {
        int floor;
        Direction direction;
    }

    /*
        Time: O(n) -> where n = number of elevators.
        Space: O(1) -> since only a couple of variables are used.
     */
    public int callElevator(List<Elevator> elevators, int passengerFloor, Direction passengerDir) {
        elevators.sort(Comparator.comparingInt(e -> e.getDirection().ordinal()));

        int bestIndex = -1;
        int bestDistance = Integer.MAX_VALUE;

        for(int i=0; i<elevators.size(); i++) {
            if(canPick(elevators.get(i), passengerFloor, passengerDir)) {
                int distance = Math.abs(elevators.get(i).getFloor() - passengerFloor);
                if (distance < bestDistance) {
                    bestIndex = i;
                    bestDistance = distance;
                }
            }
        }

        return bestIndex;
    }

    public boolean canPick(Elevator elevator, int passengerFloor, Direction passengerDir) {
        if(elevator.direction == Direction.IDLE)
            return true;

        if(elevator.direction == Direction.UP)
            return Direction.UP.equals(passengerDir) && elevator.floor <= passengerFloor;

        if(elevator.direction == Direction.DOWN)
            return Direction.DOWN.equals(passengerDir) && elevator.floor >= passengerFloor;

        return false;
    }

}
