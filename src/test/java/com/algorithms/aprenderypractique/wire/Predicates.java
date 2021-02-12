package com.algorithms.aprenderypractique.wire;

import java.util.function.Predicate;

public class Predicates {

    static Predicate<Integer> checkEven = t -> t % 2 == 0;

    static Predicate<Integer> checkOdd = t -> t % 2 == 1;

    static Predicate<Integer> isDivisibleByFive = t -> t % 5 == 0;

}
