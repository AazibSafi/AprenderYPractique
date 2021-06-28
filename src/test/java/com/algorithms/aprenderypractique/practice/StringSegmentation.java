package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *  https://www.educative.io/m/string-segmentation
 *
 *  {hello} {now}
 */
public class StringSegmentation extends BaseTest {

    @Test
    public void test() {
        Set < String > dictionary = new HashSet<>();
        String s = "hellonow";

        dictionary.add("hello");
        dictionary.add("hell");
        dictionary.add("on");
        dictionary.add("now");

        if (canSegmentString(s, dictionary)) {
            System.out.println("String Can be Segmented");
        }
        else {
            System.out.println("String Can NOT be Segmented");
        }
    }

    public boolean canSegmentString(String str, Set<String> dictionary) {

        for(int i=1;i<str.length();i++) {
            String first = str.substring(0,i);

            if(dictionary.contains(first)) {
                String second = str.substring(i);

                if(Strings.isBlank(second) || dictionary.contains(first)
                        || canSegmentString(second,dictionary) ) {
                    return true;
                }
            }

        }
        return false;
    }

}
