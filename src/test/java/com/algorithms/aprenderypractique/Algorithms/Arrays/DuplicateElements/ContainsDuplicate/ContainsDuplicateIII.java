package com.algorithms.aprenderypractique.Algorithms.Arrays.DuplicateElements.ContainsDuplicate;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *      https://leetcode.com/problems/contains-duplicate-iii
 *      https://www.youtube.com/watch?v=x9pdHIxHgWA&ab_channel=CodingDecoded
 */
public class ContainsDuplicateIII extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
        Assert.assertFalse(containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
        Assert.assertTrue(containsNearbyAlmostDuplicate(new int[]{99,1,7,4,3,12}, 4, 3));
    }

    // Java's division `/` rounds towards zero, but we need floor division for correct bucketing.
    private long getBucketId(long item, long width) {
        return Math.floorDiv(item, width);  // bucket label is x/w
    }

/*
    Approach#3 - Buckets
    Time: O(n)
    Space: O(min(n,k))
*/
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        Map<Long, Long> buckets = new HashMap<>();  // Buckets contains at most one element at any time
        Long width = valueDiff + 1l;

        for(int i=0; i<nums.length; i++) {
            long id = getBucketId(nums[i], width);

            // check if current bucket is empty, each bucket may contain at most one element
            if(buckets.containsKey(id)) return true;

            // check the neighbor buckets
            if(buckets.containsKey(id-1) &&
                    Math.abs(nums[i] - buckets.get(id-1)) <= valueDiff)
                return true;

            if(buckets.containsKey(id+1) &&
                    Math.abs(nums[i] - buckets.get(id+1)) <= valueDiff)
                return true;

            // now bucket is empty and no almost duplicate in neighbor buckets
            buckets.put(id, (long) nums[i]);

            if(buckets.size() > indexDiff)
                buckets.remove(getBucketId(nums[i-indexDiff], width));
        }
        return false;
    }

/*
    Approach#2 - Binary Search Tree
    Time: O(nlog(min(n,k))
    Space: O(min(n,k))

    - If elements in the window are in sorted order, we can apply Binary Search twice to search for the two boundaries x+t and x−t for each element x.
    - Using Long to avoid causing overflow in arithmetic operation. i.e; 2 - (-2) = 4
*/
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0; i<nums.length; i++) {          // O(n)
            // Find the successor of current element
            Integer ceil = set.ceiling(nums[i]);    // O(logK)
            if(ceil != null && Math.abs((long)ceil - nums[i]) <= valueDiff)
                return true;

            // Find the predecessor of current element
            Integer floor = set.floor(nums[i]);     // O(logK)
            if(floor != null && Math.abs(nums[i] - (long)floor) <= valueDiff)
                return true;

            set.add(nums[i]);
            if(set.size() > indexDiff)
                set.remove(nums[i-indexDiff]);
        }
        return false;
    }

/*
    Approach#1 - Naive Linear Search - Brute Force
    Time Limit Exceeded
    Time: O(n.min(k,n))
    Space: O(1)
*/
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int indexDiff, int valueDiff) {
        for(int i=0; i<nums.length; i++) {      // O(n)
            for(int j=i+1; j<=i+indexDiff && j<nums.length; j++) {  // O(k)
                if(Math.abs(nums[i] - nums[j]) <= valueDiff)
                    return true;
            }
        }
        return false;
    }

}
