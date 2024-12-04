package com.algorithms.aprenderypractique.algorithm.arrays.MathOperations;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *      https://leetcode.com/problems/distinct-prime-factors-of-product-of-array
 *
 *      Prerequisit
 *      @see CountPrimes
 *
 */
public class DistinctPrimeFactorsOfProductOfArray extends BaseTest {

/*
    Time: O(N * Sqrt(K))
    Space: O(K)
*/
    Boolean[] primes;
    public int distinctPrimeFactors(int[] nums) {
        primes = findAllPrimeNumbers(1000+1);
        Set<Integer> distinctPrimes = new HashSet<>();

        for(int n : nums) {
            if(primes[n])
                distinctPrimes.add(n);
            else {
                for(int f=2; f<=n/2; f++) {
                    if(n%f==0 && primes[f])
                        distinctPrimes.add(f);
                }
            }
        }
        return distinctPrimes.size();
    }

    public Boolean[] findAllPrimeNumbers(int k) {
        Boolean[] primes = new Boolean[k];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for(int i=2; i*i<k; i++) {
            if(primes[i]) {
                for(int factor=i; i*factor<k; factor++) {
                    primes[i*factor] = false;
                }
            }
        }
        return primes;
    }

}
