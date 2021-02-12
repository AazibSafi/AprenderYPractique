package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

public class Pascals extends BaseTest {

    Binomial binomial = new Binomial();


    @Test
    public void solution() {
        printPascalsTriangle_N_Rows(5);
        System.out.println();
        printPascalsTriangle_Kth_Row(4);


    }

    void printPascalsTriangle_N_Rows(int n) {
        for(int k=0;k<n;k++) {
            printPascalsTriangleBinomial_Kth_Row(k);
            //printPascalsTriangle_Kth_Row(k);
            System.out.println();
        }
    }

    void printPascalsTriangle_Kth_Row(int k) {
        int C=1;
        for(int j=0;j<k+1;j++) {
            System.out.print(C+" ");
            C = C * (k - j) / j;
            //System.out.print(calculate(k,j));
        }
    }

    void printPascalsTriangleBinomial_Kth_Row(int k) {
        for(int j=0;j<k+1;j++) {
            System.out.print(binomial.binomial(k,j));
        }
    }

    int calculate(int i, int j) {
        if(j==0 || i==j )
            return 1;

        if(j>i || j<0)
            return 0;

        return calculate(i-1,j-1) + calculate(i-1,j);
    }

}
