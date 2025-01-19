package com.algorithms.aprenderypractique.Algorithms.Arrays.Bits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/number-of-1-bits
 */
public class NumberOfBits {

    @ParameterizedTest
    @MethodSource("testCases")
    public void testHammingWeight(int n, int expectedOutput) {
        Assertions.assertEquals(expectedOutput, hammingWeight(n));
        Assertions.assertEquals(expectedOutput, hammingWeight2(n));
        Assertions.assertEquals(expectedOutput, hammingWeight_Recursive(n));
        Assertions.assertEquals(expectedOutput, hammingWeight_builtInMethod(n));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(11, 3),          // Binary: 1011 -> 3 ones
                Arguments.of(128, 1),         // Binary: 10000000 -> 1 one
                Arguments.of(2147483645, 30), // Binary: 1111111111111111111111111111101 -> 30 ones
                Arguments.of(0, 0),           // Binary: 0 -> 0 ones
                Arguments.of(1, 1),           // Binary: 1 -> 1 one
                Arguments.of(7, 3),           // Binary: 111 -> 3 ones
                Arguments.of(1023, 10),       // Binary: 1111111111 -> 10 ones
                Arguments.of(Integer.MAX_VALUE, 31) // Binary: 1111111111111111111111111111111 -> 31 ones
        );
    }

/*
    Logic:
        The number of 1 bits in N integer = (n/2 + n%2)
        if the number is odd, then the number of 1 bits in N would 1 plus it's half number
        if the number is Even, then number of 1 bits in N would be same as it's half number.

        Here n%2 will only give O (for Even) or 1 (for Odd)
 */

/*
    Time: O(logN)
    Space: O(1)
*/
    public int hammingWeight(int n) {
        int count = 0;
        while(n>0) {
            count += n%2;
            n/=2;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        while(n>0) {
            if(n%2==1)
                count++;
            n/=2;
        }
        return count;
    }

/*
    Time: O(n)
    Space: O(n)
*/
    public int hammingWeight_Recursive(int n) {
        if(n==0 || n==1) return n;
        return hammingWeight(n/2) + n%2;
    }

//  No builtin function is Acceptable
//  This is just for understanding the function
    public int hammingWeight_builtInMethod(int n) {
        return Integer.bitCount(n);
    }

}
