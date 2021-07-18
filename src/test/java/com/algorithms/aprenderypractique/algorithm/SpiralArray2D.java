package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Test;

/**
 *  Find the pattern and complete the function
 *  https://www.youtube.com/watch?v=1ZGJzvkcLsA
 */
public class SpiralArray2D extends BaseTest {

    @Test
    public void test() {
        CommonHelper.print2DArray(spiralOrder(5,5));
    }

    public int[][] spiralOrder(int m, int n) {
        int[][] A = new int[m][n];
        int x = 1;

        int top = 0;    int down = m-1;   int left = 0;   int right = n-1;
        int dir = 0;

        while(left<=right && top<=down) {

            if(dir == 0) {
                for(int i=left; i<=right; i++) {
                    A[top][i] = x++;
                }
                top++;
            }
            if(dir == 1) {
                for(int i=top; i<=down; i++) {
                    A[i][right] = x++;
                }
                right--;
            }
            if(dir == 2) {
                for(int i=right; i>=left; i--) {
                    A[down][i] = x++;
                }
                down--;
            }
            if(dir == 3) {
                for(int i=down; i>=top; i--) {
                    A[i][left] = x++;
                }
                left++;
            }

            dir = (dir+1)%4;
        }
        return A;
    }

}
