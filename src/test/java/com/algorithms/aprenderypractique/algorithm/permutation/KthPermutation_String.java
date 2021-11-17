package com.algorithms.aprenderypractique.algorithm.permutation;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * For Strings
 * https://www.youtube.com/watch?v=W9SIlE2jhBQ
 */
public class KthPermutation_String extends BaseTest {

    @Test
    public void test() {
        String sequence = "ABCD";
        Assert.assertEquals("ADCB", findKthPermutation(sequence,6));
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
    public String findKthPermutation(String sequence, int k) {

        List<Character> sequenceList = breakTheSequence(sequence);
        int n = sequenceList.size();
        StringBuilder kthPermutation = new StringBuilder();

        while(n > 1) {
            int index = k/fact(n-1);

            if(k % fact(n-1) == 0) {
                index--;
            }

            kthPermutation.append(sequenceList.get(index));

            sequenceList.remove(index);

            k = k - fact(n-1)*index;
            n--;
        }

        kthPermutation.append(sequenceList.get(0));

        return kthPermutation.toString();
    }

    private List<Character> breakTheSequence(String sequence) {
        return sequence.chars().mapToObj(elm -> (char) elm).collect(Collectors.toList());
    }

    private int fact(int x) {
        if(x == 0 || x == 1) {
            return 1;
        }
        return x*fact(x-1);
    }

}
