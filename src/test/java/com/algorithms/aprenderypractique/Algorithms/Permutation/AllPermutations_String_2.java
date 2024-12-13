package com.algorithms.aprenderypractique.Algorithms.Permutation;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 */
public class AllPermutations_String_2 extends BaseTest {

    @Test
    public void test() {
        List<String> permutations = Arrays.asList("ABC","ACB","BAC","BCA","CAB","CBA");
        Assert.assertTrue(permutations.containsAll( permute("ABC") ));

        permutations = Arrays.asList("geek","geke","gkee","egek","egke","eegk","eekg","ekge","ekeg","kgee","kege","keeg");
        Assert.assertTrue(permutations.containsAll( permute("geek") ));

        permutations = Arrays.asList("abb","abb","bab","bba","bab","bba");
        Assert.assertTrue(permutations.containsAll( permute("abb") ));
    }

    Map<String, List<String>> cache = new HashMap<>();

/*
    Different Technique
    Long code but just for understanding different technique

    ABC - Fix each character and call permutation of the rest of the string and then merge them
 */
    public List<String> permute(String str) {
        if(str.length() == 1)   return Collections.singletonList(str);

        List<String> permutations = new ArrayList<>();

        for(int i=0; i<str.length(); i++) {
            String prefix = str.charAt(i)+"";
            String remainingString = str.substring(0,i) + str.substring(i+1);

            List<String> suffixPermutations;
            if(cache.containsKey(remainingString))
                suffixPermutations = cache.get(remainingString);
            else {
                suffixPermutations = permute(remainingString);
                cache.put(remainingString, suffixPermutations);
            }

            for(String suffix : suffixPermutations) {
                permutations.add(prefix + suffix);
            }
        }

        return permutations;
    }

}
