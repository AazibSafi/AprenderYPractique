package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/*
    https://www.youtube.com/watch?v=BysNXJHzCEs&ab_channel=TusharRoy-CodingMadeSimple
    Longest Common Substring
 */
public class LongestCommonSubstring extends BaseTest {

    @Test
    public void test() {
        String str1 = "abcdef";
        String str2 = "aabcddbcdef";
        System.out.println(longestCommonSubstringLength(str1,str2));
        System.out.println(longestCommonSubstring(str1,str2));
        Assert.assertEquals("bcdef",longestCommonSubstring(str1,str2));
    }

//     O(N*M)
    public int longestCommonSubstringLength(String str1, String str2) {
        int iMax=0,jMax=0;
        int[][] T = new int[str1.length()][str2.length()];
        for(int i=0;i<str1.length();i++) {
            for(int j=0;j<str2.length();j++) {
                if(str1.charAt(i)==str2.charAt(j)) {
                    T[i][j] = i-1<0||j-1<0 ? 1: T[i-1][j-1]+1;

// the element can directly be saved here instead of saving index -> MaxLength = T[i][j] and then return MaxLength in the end
                    if(T[iMax][jMax] < T[i][j]) {
                        iMax = i;
                        jMax = j;
                    }
                }
            }
        }
        return T[iMax][jMax];
    }

//     O(N*M) -- Extension of above method
    public String longestCommonSubstring(String str1, String str2) {
        int iMax=0,jMax=0;
        int[][] T = new int[str1.length()][str2.length()];
        for(int i=0;i<str1.length();i++) {
            for(int j=0;j<str2.length();j++) {
                if(str1.charAt(i)==str2.charAt(j)) {
                    T[i][j] = i-1<0||j-1<0 ? 1: T[i-1][j-1]+1;

                    if(T[iMax][jMax] < T[i][j]) {
                        iMax = i;
                        jMax = j;
                    }
                }
            }
        }

        /*
            Traverse the Table T[][] diagonally from the iMax,jMax till it find a 0 element
         */
        StringBuilder commonSubstring = new StringBuilder();

        while(iMax>0 && jMax>0 && T[iMax][jMax]!=0) {
            commonSubstring.insert(0, str1.charAt(iMax));
            iMax--; jMax--;
        }

        return commonSubstring.toString();
    }

}
