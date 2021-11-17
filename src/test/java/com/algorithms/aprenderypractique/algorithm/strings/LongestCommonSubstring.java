package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Traverse;
import org.junit.Assert;
import org.junit.Test;

/*
    https://www.youtube.com/watch?v=BysNXJHzCEs&ab_channel=TusharRoy-CodingMadeSimple
    Longest Common Substring
 */
public class LongestCommonSubstring extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("bcdef",longestCommonSubstring("abcdef","aabcddbcdef"));
        Assert.assertEquals("bcd",longestCommonSubstring("abcdaf","zbcdf"));
    }

//     Time: O(N*M)
/*
 Formula:
    if( iCHAR == jCHAR )
        diagonal + 1
 */
    public String longestCommonSubstring(String str1, String str2) {
        int iMax=0,jMax=0;
        int[][] dp = new int[str1.length()][str2.length()];
        for(int i=0;i<str1.length();i++) {
            for(int j=0;j<str2.length();j++) {
                if(str1.charAt(i)==str2.charAt(j)) {
                    dp[i][j] = i-1<0||j-1<0 ? 1: dp[i-1][j-1]+1;

                    if(dp[iMax][jMax] < dp[i][j]) {
                        iMax = i;
                        jMax = j;
                    }
                }
            }
        }

//      if Only Length required in the Question
//          return T[iMax][jMax];


//      Traverse the Table T[][] diagonally from the iMax,jMax till it find a 0 element
        StringBuilder commonSubstring = new StringBuilder();

        while(iMax>0 && jMax>0 && dp[iMax][jMax]!=0) {
            commonSubstring.insert(0, str1.charAt(iMax));
            iMax--; jMax--;
        }

        return commonSubstring.toString();
    }

}
