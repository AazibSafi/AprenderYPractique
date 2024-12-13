package com.algorithms.aprenderypractique.Algorithms.Permutation;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 */
public class AllPermutations_String extends BaseTest {

    @Test
    public void test() {
        List<String> permutations = Arrays.asList("ABC","ACB","BAC","BCA","CAB","CBA");
        Assert.assertTrue(permutations.containsAll( permute("ABC") ));

        permutations = Arrays.asList("geek","geke","gkee","egek","egke","eegk","eekg","ekge","ekeg","kgee","kege","keeg");
        Assert.assertTrue(permutations.containsAll( permute("geek") ));

        permutations = Arrays.asList("abb","abb","bab","bba","bab","bba");
        Assert.assertTrue(permutations.containsAll( permute("abb") ));
    }

    public List<String> permute(String str) {
        List<String> output = new ArrayList<>();
        backtracking("",str, output);
        return output;
    }

    public void backtracking(String prefix, String str, List<String> output) {
        if(str.length() == 0)   output.add(prefix);

        Map<Character,Boolean> alpha = new HashMap<>();

        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);

            String rem = str.substring(0,i) + str.substring(i+1);       // Rest of the string after excluding the ith character

            if(!alpha.getOrDefault(ch,false))       // If the character has not been used then recursive call will take place.
                backtracking(prefix + ch, rem, output);

            alpha.put(ch, true);
        }
    }

}
