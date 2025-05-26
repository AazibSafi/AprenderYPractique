package com.algorithms.aprenderypractique.Algorithms.Design;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/design-file-system
 *
 *      @see
 *      com.algorithms.aprenderypractique.interviews.Google.FileSystem
 */
public class DesignFileSystem {
    /*
        Approach: HashMap
        Space Heavy solution
    */
    class FileSystem {
        Map<String, Integer> map;

        public FileSystem() {
            map = new HashMap<>();
            map.put("", -1);    // To have a root
        }

        public boolean createPath(String path, int value) {
            if( map.containsKey(path)
                    || !map.containsKey(path.substring(0, path.lastIndexOf("/"))))
                return false;

            map.put(path, value);
            return true;
        }

        public int get(String path) {
            return map.getOrDefault(path, -1);
        }
    }

    /*
        Approach: Trie Based Dictionary
        Let T nodes in the Trie
        Time:
            create: O(T) to add a path to the trie if it contains T components.
            get: O(T) to find a path in the trie if it contains T components.
        Space:
            create: O(T) to add a path to the trie if it contains T components.
            get: O(1)
    */
    class FileSystem2 {
        Trie trie;

        public FileSystem2() {
            trie = new Trie("", -1, new HashMap<>());
        }

        public boolean createPath(String path, int value) {
            String[] components = path.split("/");
            int n = components.length;
            Trie curr = trie;

            for(int i=1; i<n; i++) {
                String component = components[i];

                // if it exists in the current node's dictionary
                if(curr.children.containsKey(component)) {
                    curr = curr.children.get(component);
                }
                // If it is the last node, add it to the Trie.
                else if(i == n-1) {
                    curr.children.put(component, new Trie(component, value, new HashMap<>()));
                    return true;
                }
                else    // the parent path doesn't exist
                    return false;
            }

            // Value not equal to -1 means the path already exists in the trie.
            if (curr.value != -1) {
                return false;
            }

            curr.value = value;
            return true;
        }

        public int get(String path) {
            String[] components = path.split("/");
            Trie curr = trie;

            for(int i=1; i<components.length; i++) {
                String component = components[i];
                if(!curr.children.containsKey(component))
                    return -1;

                curr = curr.children.get(component);
            }
            return curr.value;
        }
    }

    class Trie {
        String path;
        int value;
        Map<String, Trie> children;
        public Trie(String path, int value, Map<String, Trie> children) {
            this.path = path;
            this.value = value;
            this.children = children;
        }
    }

}
