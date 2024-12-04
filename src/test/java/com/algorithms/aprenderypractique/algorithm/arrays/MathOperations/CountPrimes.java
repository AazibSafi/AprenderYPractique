package com.algorithms.aprenderypractique.algorithm.arrays.MathOperations;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.Arrays;

/**
 *      https://leetcode.com/problems/count-primes
 *
 *      Next
 *      @see DistinctPrimeFactorsOfProductOfArray
 */
public class CountPrimes extends BaseTest {

/*
    Time: O(sqrt(n).log(logn)) + n)
    Space: O(n)
    Approach: Sieve of Eratosthenes
*/
    public int countPrimes(int n) {
        if(n <= 2) return 0;    // Edge case:  0 and 1 are not prime

        Boolean[] prime = new Boolean[n];
        boolean[] primes = new boolean[n];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;    // 0 and 1 are not prime

        // i < sqrt(n) <==> i*i < n
        for(int i=2; i*i<n; i++) {      // O(sqrt(n))
            if(prime[i]) {
                for(int j=i; i*j<n; j++) {      // O(log(logn))
                    prime[i*j] = false;     // Mark Composite - MArk multiples of i as non-primes
                }
            }
        }

        return (int) Arrays.stream(prime).filter(p -> p==true).count();     // O(n)
    }

}
