package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 *      https://leetcode.com/problems/reorganize-string
 *
 *      Todo: https://leetcode.com/problems/rearrange-string-k-distance-apart
 *
 * @see com.algorithms.aprenderypractique.InterviewDB.Pinterest.ReorganizePins.ReorganizePins
 */
public class ReorganizeString extends BaseTest {

    @Test
    public void solution() {
        Assert.assertEquals("aba", reorganizeString("aab"));
        Assert.assertEquals("", reorganizeString("aaab"));
        Assert.assertEquals("vlvov", reorganizeString("vvvlo"));
        Assert.assertTrue(Arrays.asList("ababacacbd", "ababcabacd").contains(reorganizeString("aabbcaabcd")));
    }

    public String reorganizeString2(String s) {
        // count frequency
        int[] freq = new int[26];
        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // pq
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1]-a[1]); // Max Heap

        // add freq in pq
        for(int i=0; i<freq.length; i++) {
            if(freq[i] > 0) {
                pq.offer(new int[]{i+'a', freq[i]});
            }
        }

        StringBuilder sb = new StringBuilder();

        while(pq.size() >= 2) {
            int[] first = pq.poll();
            int[] second = pq.poll();

            sb.append((char) first[0]).append((char) second[0]);

            if(--first[1] > 0)  pq.offer(first);
            if(--second[1] > 0)  pq.offer(second);
        }

        if(!pq.isEmpty()) {
            int[] last = pq.poll();
            if(last[1] > 1) return "";  // no solution
            sb.append((char) last[0]);
        }

        return sb.toString();
    }

/*
    Approach#2 Counting and Odd/Even
    Time: O(n)
    Space: O(k)

    Let N be the total characters in the string.
    Let k be the total unique characters in the string.
*/
    public String reorganizeString(String s) {
        int max = 0, iMax = 0;
        int[] frequency = new int[26];
        for(char c : s.toCharArray()) {
            frequency[c - 'a']++;

            if(frequency[c - 'a'] > max) {
                max = frequency[c - 'a'];
                iMax = c - 'a';
            }
        }

        if (max > (s.length() + 1) / 2) {
            return "";       // not possible to rearrange the characters
        }

        char[] output = new char[s.length()];
        int idx = 0;

        // Place the most frequent letter at every second index (Even Index)
        while(frequency[iMax] != 0) {
            output[idx] = (char) (iMax + 'a');
            frequency[iMax]--;
            idx += 2;
        }

        // Place rest of the letters in any order
        for(int i=0; i<frequency.length; i++) {
            while(frequency[i] > 0) {
                if(idx >= s.length())    idx = 1;

                output[idx] = (char) (i + 'a');
                frequency[i]--;
                idx += 2;
            }
        }

        return String.valueOf(output);
    }

/*
    Approach#1 Counting and Priority Queue
    Time: O(nLogK)
    Space: O(k)

    For this problem, k is bounded by 26, so one could argue that the time complexity is actually O(N) and space is O(1).

    Repeatedly select the most frequent character that isn't the one previously placed. This ensures that no two adjacent characters in the rearranged string are the same.
*/
    public String reorganizeString1(String s) {
        int[] freq = new int[26];
        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);   // Max heap ordered by character counts
        IntStream.range(0, 26)
                .filter(i -> freq[i]>0)
                .forEach(
                        i -> pq.offer(new int[]{i+'a', freq[i]})
                );

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            int[] first = pq.poll();    // O(1)
            if(sb.isEmpty() || first[0] != sb.charAt(sb.length()-1)) {
                sb.append((char) first[0]);
                if(--first[1] > 0)
                    pq.offer(first);    //  O(logk)
            }
            else {
                if(pq.isEmpty())   return "";  // Impossible to rearrange the characters.

                int[] second = pq.poll();   // O(1)
                sb.append((char) second[0]);
                if(--second[1] > 0)
                    pq.offer(second);   //  O(logk)

                pq.offer(first);        //  O(logk)
            }
        }

        return sb.toString();
    }

    // Simple implementation of approach#1
    public String reorganizeString1_1(String s) {
        int[] freq = new int[26];
        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);   // Max heap ordered by character counts
        IntStream.range(0, 26)
                .filter(i -> freq[i]>0)
                .forEach(
                        i -> pq.offer(new int[]{i+'a', freq[i]})
                );

        StringBuilder sb = new StringBuilder();
        while(pq.size() >= 2) {
            int[] first = pq.poll();
            int[] second = pq.poll();

            sb.append((char) first[0]).append((char) second[0]);

            if(--first[1] > 0)  pq.offer(first);
            if(--second[1] > 0) pq.offer(second);
        }

        if(!pq.isEmpty()) {
            int[] lastChar = pq.poll();
            if(lastChar[1] > 1)
                return "";  // no solution
            sb.append((char) lastChar[0]);
        }

        return sb.toString();
    }

}
