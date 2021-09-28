package com.algorithms.aprenderypractique.Toptal.attemp2;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Task1 extends BaseTest {

    @Test
    public void test() {

    }

    public int solution(String[] T, String[] R) {
        Map<Integer, Boolean> map = getWhichGroupsPassed(T,R);

        int passedGroups = getPassedGroupsCount(map);

        return calculateResult(passedGroups,map.size());
    }

    Map<Integer, Boolean> getWhichGroupsPassed(String[] T, String[] R) {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i=0; i<T.length; i++) {
            int group = getGroupName(T[i]);
            boolean testResult = isTestPassed(R[i]);

            if(!map.containsKey(group)) {
                map.put(group,testResult);
            }
            else if(map.get(group)) {       // if it is already true
                map.put(group,testResult);
            }
        }
        return map;
    }

    int calculateResult(int passedGroups, int totalGroups) {
        return Math.round( (passedGroups * 100) / totalGroups );
    }

    int getPassedGroupsCount(Map<Integer, Boolean> map) {
        int count = 0;
        for(Map.Entry<Integer,Boolean> test : map.entrySet()) {
            if(test.getValue())   count++;
        }
        return count;
    }

    int getGroupName(String test) {
        char c = test.charAt(test.length()-1);
        if(!Character.isDigit(c)) {
            c = test.charAt(test.length()-2);
        }
        return Integer.parseInt(c+"");
    }

    boolean isTestPassed(String testResult) {
        return testResult.equals("OK");
    }

}
