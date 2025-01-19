package com.algorithms.aprenderypractique.Algorithms.Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *      https://leetcode.com/problems/design-an-ordered-stream
 *      https://leetcode.com/problems/design-an-ordered-stream/solutions/2666265/bloomberg-question-with-variation
 *
 *      Similar Concept in this interview asked question
 *          https://leetcode.com/discuss/interview-question/340230/google-onsite-implement-logger
 */
public class DesignAnOrderedStream {
/*
    Minor Variation: Not given size in advance.
    Without the use of N
*/
    class OrderedStream {
        int ptr;
        Map<Integer, String> stream;

        public OrderedStream(int n) {
            this.ptr = 1;
            this.stream = new HashMap<>();
        }

        public List<String> insert(int idKey, String value) {
            stream.put(idKey, value);

            List<String> res = new ArrayList<>();
            if(ptr == idKey) {
                while(ptr<=stream.size() && stream.containsKey(ptr)) {
                    res.add(stream.get(ptr));
                    ptr++;
                }
            }
            return res;
        }
    }

    class OrderedStream2 {
        int ptr;
        String[] stream;

        public OrderedStream2(int n) {
            this.ptr = 0;
            this.stream = new String[n];
        }

        public List<String> insert(int idKey, String value) {
            stream[idKey-1] = value;

            List<String> res = new ArrayList<>();
            while(ptr<stream.length && stream[ptr] != null) {
                res.add(stream[ptr]);
                ptr++;
            }
            return res;
        }
    }
}