package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 *  https://leetcode.com/problems/simplify-path/
 *  https://www.youtube.com/watch?v=kVylYewnQ_k&ab_channel=NareshGupta
 *  https://www.youtube.com/watch?v=qYlHrAKJfyA&ab_channel=NeetCode
 */
public class SimplifyPath extends BaseTest {

    @Test
    public void testIndex() {
        String path = "/home/";
        Assert.assertEquals("/home", simplifyPath(path));

        path = "/../";
        Assert.assertEquals("/", simplifyPath(path));

        path = "/home//foo/";
        Assert.assertEquals("/home/foo", simplifyPath(path));

        path = "/a/./b/../../c/";
        Assert.assertEquals("/c", simplifyPath(path));

        path = "/../../../a/b/c/";
        Assert.assertEquals("/a/b/c", simplifyPath(path));
    }

/*
    Time: O(N)
    Space: O(N)

    Using Stack Technique
 */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] tokens = path.split("/");

        for(String token : tokens) {
            if("..".equals(token) && !stack.isEmpty())
                stack.pop();
            else if(!Arrays.asList("",".","..").contains(token))
                stack.push(token);
        }

        return "/" + String.join("/", stack);
    }

//  Time: O(N)
    public String simplifyPath2(String path) {
        StringBuilder str = new StringBuilder();
        String[] tokens = path.split("/");

        for(String file : tokens) {
            if(file.isEmpty() || file.equals("."))
                continue;

//  Delete all previous chars from builder until it finds a '/'
            if(file.equals("..")) {
                int lastChar = str.length()-1;
                while(str.length() != 0 && str.charAt(lastChar) != '/') {
                    str.deleteCharAt(lastChar);
                    lastChar = str.length()-1;
                }

                if(str.length() != 0)      str.deleteCharAt(lastChar);
            }
            else {
                str.append('/').append(file);
            }
        }

        if(str.length() == 0)      return "/";
        return str.toString();
    }

}
