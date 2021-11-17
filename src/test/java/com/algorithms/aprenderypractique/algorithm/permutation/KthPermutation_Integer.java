package com.algorithms.aprenderypractique.algorithm.permutation;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    For Integers
 * https://www.youtube.com/watch?v=W9SIlE2jhBQ
*/

public class KthPermutation_Integer extends BaseTest {

    @Test
    public void test() {
        int sequence = 1234;
        System.out.println(findKthPermutation(sequence,12));
    }

/*
        loop through size of list till n-1
            get the index by formula
            check the boundary of index
            get the item from list at index
            add that item into finalResult
            remove that item from sequence list
            decrease size of sequence
            update the K val
        add the last item from the list into the finalResult

        Total Permutation -> n!
        Block Size -> (n-1)!
        Index -> k/(n-1)!
 */

    public Integer findKthPermutation(Integer sequence, int k) {

        List<Integer> sequenceList = breakTheSequence(sequence);
        int n = sequenceList.size();
        Integer kthPermutation = 0;

        while(n > 1) {
            int index = k/fact(n-1);
            if(k % fact(n-1) == 0) {
                index--;
            }

            kthPermutation = addInTheResultPermutation(kthPermutation, sequenceList.get(index) );

            sequenceList.remove(index);

            k = k - fact(n-1)*index;
            n--;
        }

        kthPermutation = addInTheResultPermutation(kthPermutation, sequenceList.get(0) );

        return kthPermutation;
    }

    private Integer addInTheResultPermutation(Integer kthPermutation, Integer item) {
        if(kthPermutation == null ) {
            kthPermutation = 0;
        }
        return kthPermutation*10 + item;
    }

    private List<Integer> breakTheSequence(Integer sequence) {
        List<Integer> sequenceList = new ArrayList<>();
        while(sequence != 0) {
            int remainder = sequence % 10;
            sequenceList.add(0,remainder);

            sequence /= 10;
        }
        return sequenceList;
    }

    private int fact(int x) {
        if(x == 0 || x == 1) {
            return 1;
        }
        return x*fact(x-1);
    }

}
