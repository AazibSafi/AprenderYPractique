package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FirstDuplicateElement extends BaseTest {

    @Test
    public void solution() {
        System.out.println(firstDuplicate(new int[]{2,1,3,5,1,3,2}));
    }

/*
    Time Complexity: O(N)
    Space Complexity: O(1)
 */
    int firstDuplicate(int[] a) {
        for(int i=0;i<a.length;i++) {
            if(a[Math.abs(a[i])-1]<0) {
                return Math.abs(a[i]);
            }
            else {
                a[Math.abs(a[i])-1] = -a[Math.abs(a[i])-1];
            }
        }
        return -1;
    }

    //   O(N) + Space Complexity
    int firstDuplicate2(int[] a) {
        List list = new ArrayList<>();

        for(int x : a) {
            if(list.contains(x)) {
                return x;
            }
            list.add(x);
        }
        return -1;
    }

}
