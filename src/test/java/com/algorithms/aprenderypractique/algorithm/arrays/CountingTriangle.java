package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public class CountingTriangle extends BaseTest {

    @Test
    public void test() {
        ArrayList<Sides> arr = new ArrayList<>();
        arr.add(new Sides(7, 6, 5));
        arr.add(new Sides(5, 7, 6));
        arr.add(new Sides(8, 2, 9));
        arr.add(new Sides(2, 3, 4));
        arr.add(new Sides(2, 4, 3));
        Assert.assertEquals(3, countDistinctTriangles(arr));

        arr = new ArrayList<>();
        arr.add(new Sides(3, 4, 5));
        arr.add(new Sides(8, 8, 9));
        arr.add(new Sides(7, 7, 7));
        Assert.assertEquals(3, countDistinctTriangles(arr));
    }

    int countDistinctTriangles(ArrayList<Sides> arr) {
        return new HashSet(arr).size();
    }

    class Sides {
        int a;
        int b;
        int c;
        Sides(int a,int b,int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

//  Added
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Sides)) return false;
            Sides sides = (Sides) o;
            return (sides.a+sides.b+sides.c) == (a+b+c);
        }

        @Override
        public int hashCode() {
            return a+b+c;
        }
    }

}
