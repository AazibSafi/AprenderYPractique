package com.algorithms.aprenderypractique.wire;
/*
Given a collection of numbers write a Java program to calculate the sum and product of all even numbers. 
How can the program be changed to support a general aggregation of the numbers (like the sum) selected by a general criterion (like the even numbers).
public interface Predicate<T>
boolean	test(T t)
Evaluates this predicate on the given argument.
*/

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.io.IOException;
import java.util.function.Predicate;

public class MyClass extends BaseTest {

    @Test
    public static void main(String args[]) throws IOException {
        int arr[] = new int[]{1, 2, 3, 4, 5, 6, 10};

        ArithmeticOperations addition = new Addition();
        ArithmeticOperations product = new Multiplication();

        System.out.println("Sum--> " + result(arr, Predicates.checkEven, addition));
        System.out.println("Prod--> " + result(arr, Predicates.checkOdd, product));
        System.out.println("Prod--> " + result(arr, Predicates.isDivisibleByFive, product));
    }

    private static int result(int[] arr, Predicate criteria, ArithmeticOperations operator) throws IOException {
        int result = 0;
        if (operator instanceof Multiplication) {
            result = 1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (criteria.test(arr[i])) {
                result = operator.doOperation(result, arr[i]);
            }
        }
        return result;
    }

}