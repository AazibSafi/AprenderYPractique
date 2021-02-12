package com.algorithms.aprenderypractique;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class WaleedTest extends BaseTest {

    @Test
    public void test() {
        //int[] arr = new int[]{1,2,3,3,1,3,1};
        //int[] arr = new int[]{2,2,3,2,1,3,1};
        //int[] arr = new int[]{1,4,3,4,1,3,1};
        //int[] arr = new int[]{1,4,4,1,4,1};
        int[] arr = new int[]{1,4,4,1,4,1};
        int m = 4;
        System.out.println(solution(m,arr));
    }

    public int solution(int M, int[] A) {
        int N = A.length;
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)
            count[i] = 0;
        int maxOccurence = 0;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (count[A[i]] > 0) {
                int tmp = count[A[i]];
                if (tmp > maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                }
                count[A[i]] = tmp + 1;
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }

    //0 1 2 3 4
    //0 2 0 2 1

    void print(List list) {
        list.stream().forEach(s -> System.out.println(s));
    }

    void print(Map map) {
        map.forEach((X,Y)->{
            System.out.println(X+" "+Y);
        });
    }

}
