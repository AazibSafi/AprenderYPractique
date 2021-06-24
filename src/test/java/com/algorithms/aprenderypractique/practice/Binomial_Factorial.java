package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Binomial_Factorial extends BaseTest {

    @Test
    public void test() {
//        System.out.println(solution(5, 3));
//        System.out.println(solution(40, 20));
//        System.out.println(solution(20, 40));
//        System.out.println(solution(20, 21));
//        System.out.println(solution(3, 5));
//        System.out.println(solution(2, 2));
//        System.out.println(solution(6, 12));
        System.out.println(solution(12, 6));
    }

    Map<Integer, Integer> inMemoryFactorial;

    public int solution(int N, int K) {
        int result;
        if (N < 0 || K < 0 || K > N) {
            result = -1;
        }
        else {
            initializeCacheFactorial();

            try {
                result = binomial(N, K);
            }
            catch (ArithmeticException e) {
                result = -1;
            }
        }
        return result;
    }

    public void initializeCacheFactorial() {
        inMemoryFactorial = new ConcurrentHashMap<>();
        inMemoryFactorial.put(1, 1);
        inMemoryFactorial.put(0, 1);
    }

    public int binomial(int N, int K) {
        return factorial(N) / (factorial(K) * factorial(N - K));
    }

    public int factorial(int n) {
        if (inMemoryFactorial.get(n) != null) {
            return inMemoryFactorial.get(n);
        }

        int x = n * factorial(n - 1);
        inMemoryFactorial.put(n, x);
        return x;
    }

}
