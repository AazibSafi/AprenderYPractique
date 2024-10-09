package com.algorithms.aprenderypractique.algorithm.arrays.Intervals.MaxNoOfEvents;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 *      https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii
 *      https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/solutions/4518880/easy-java-solution-detailed-explanation-both-linear-binary-search/
 *      https://www.youtube.com/watch?v=C3r4OTOmfaI
 *
 *      Todo:
 *      https://leetcode.com/problems/maximum-earnings-from-taxi
 *      https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference
 *      https://leetcode.com/problems/maximum-profit-in-job-scheduling
 *
 */
public class MaximumNumberOfEventsThatCanBeAttendedII extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(7, maxValue(new int[][]{{1,2,4},{3,4,3},{2,3,1}}, 2));
        Assert.assertEquals(10, maxValue(new int[][]{{1,2,4},{3,4,3},{2,3,10}}, 2));
        Assert.assertEquals(9, maxValue(new int[][]{{1,1,1},{2,2,2},{3,3,3},{4,4,4}}, 3));
        Assert.assertEquals(57, maxValue(new int[][]{{21,77,43},{2,74,47},{6,59,22},{47,47,38},{13,74,57},{27,55,27},{8,15,8}}, 4));
        Assert.assertEquals(34368847, maxValue(new int[][]{{609160999,612582699,443422},{947166815,953380719,500128},{509665293,518745581,149858},{625034765,625478636,483866},{781784820,782291828,143346},{174077331,177609753,180156},{800863484,802150672,320499},{379280423,386305516,744771},{972166957,973110948,79556},{692103213,692598640,469358},{108578958,116774463,345270},{256592151,260217350,664230},{268985479,286614436,166394},{42376830,44672481,667237},{530474061,531435025,452033},{180856546,188537226,395742},{338388551,342982219,401949},{507351469,507913727,549999},{154302831,156765309,406069},{290607284,300207459,948892},{888281045,892881727,248408},{446315936,447061616,104646},{815494172,825477446,398940},{756050332,776209136,521588},{779316632,779549080,963107},{869704183,872729766,5720},{462503443,469555485,895241},{693441085,694978338,185090},{841934770,868854132,55140},{989043039,989602115,353190},{900176469,902174676,79381},{474974825,479638162,503580},{304661378,309185429,599881},{684127403,687829874,472919},{351618428,354094470,598001},{973498017,974205681,946253},{210602172,217465066,704733},{420357195,433111080,52113},{563437917,575174843,702873},{804172347,805364877,714162},{458919059,462050133,35108},{9000788,13150406,652181},{35828449,39094628,145541},{959143837,968532446,617744},{631923914,640240749,156275},{953553442,954876995,989019},{875555671,887619098,895006},{699569058,703133773,404319},{54860536,59914250,76485},{922504737,924918759,33579},{260430887,260483505,177717},{227082211,227559556,413159},{335782302,336897527,679183},{697081841,697880997,396999},{368668959,371316049,731831},{560095497,560975547,711041},{681339475,684097936,419920},{309355796,314934275,127300},{262996731,267567670,375651},{195847429,195906540,855488},{656383532,680387682,487017},{598421277,607100426,384007},{170054420,173421313,337141},{236881563,238498845,879035},{244664075,250331115,280780},{825480669,829419535,98586},{984255323,986838580,915106},{523252252,523269720,896046},{577361186,579854206,687101},{74202559,74304669,654998},{493376690,501763167,566016},{66486085,68558701,116854},{753219176,755396614,413036},{403217227,412032555,364961},{730136072,739387262,15180},{538674042,546547794,283571},{990161375,994240716,486296},{287295040,290465999,253402},{127475277,131381124,459808},{69961343,70828607,437234},{448033366,450427314,576909},{581073127,583845957,819991},{649107027,653839062,114275},{72670974,72791272,208621},{434126470,435830132,647362},{795717304,799240429,774654},{90483158,96047309,785088},{588970519,595272687,241911},{80939799,81840097,900259},{333231653,334320961,415819},{251486136,251901288,854597},{140956566,152440434,418734},{100247381,105571047,758200},{695750330,696415680,461183},{918078263,918890629,26172},{906947242,912435095,769132},{750796382,751146332,766352},{932653715,943945433,151639},{787417692,790849298,545930},{479778644,481390273,245309}}
                , 50));
    }

/*
    Time: O(n * log(n) * k)
    Space: O(n * k)
 */
    public int maxValue(int[][] events, int k) {
        if(k==1)        return getMaxScore(events);         // No need for below algorithm if k is 1. Simply find max score from the array

        Arrays.sort(events, (a,b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        int[][] memo = new int[events.length][k+1];
        for(int[] row : memo)
            Arrays.fill(row, -1);
        return solve(events, k, 0, -1, memo);
    }

    int solve(int[][] events, int k, int i, int prevEnd, int[][] memo) {
        if(k == 0 || i >= events.length) return 0;

        if(memo[i][k] != -1) return memo[i][k];

        int nextIdx = findNextEvent(events, i+1, events[i][1]);     // Binary Search
        int eventAttended = events[i][2] + solve(events, k - 1, nextIdx, events[i][1], memo);
        int eventNotAttended = solve(events, k, i+1, prevEnd, memo);

        return memo[i][k] = Math.max(eventAttended, eventNotAttended);
    }

//  Function to find the next available index of an event whose start time is after the current event's end time
    int findNextEvent(int[][] events, int low, int eventEnd) {
        int n = events.length;
        int high = n-1, ans = n+1;

        while(low <= high) {
            int mid = (low+high)/2;
            if(events[mid][0] > eventEnd) {     // No two events should be overlapped => startDay of next event should be greater than the endDay of current event
                ans = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return ans;
    }

    int getMaxScore(int[][] events) {
        int max = 0;
        for(int[] event : events) {
            max = Math.max(max, event[2]);
        }
        return max;
    }

}
