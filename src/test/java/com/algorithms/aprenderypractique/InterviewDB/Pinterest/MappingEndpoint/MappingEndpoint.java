package com.algorithms.aprenderypractique.InterviewDB.Pinterest.MappingEndpoint;

import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

// Pinterest Problem
public class MappingEndpoint {
    static class Trie {
        Map<String, Trie> children = new HashMap<>();
        String endpointName;    // if endpoint ends here
    }

    final Trie root = new Trie();

    public void insert(String endpoint, String endpointName) {
        String[] tokens = endpoint.split("/");
        Trie trie = root;
        for(String token : tokens) {
            trie = trie.children.computeIfAbsent(token, k -> new Trie());
        }
        trie.endpointName = endpointName;
    }

    public String resolveEndpoint(String input) {
        String[] tokens = input.split("/");
        Trie trie = root;
        for(String token : tokens) {
            if (token.isEmpty()) continue;
            if(trie.children.containsKey(token)) {
                trie = trie.children.get(token);
            }
            else if(trie.children.containsKey("<UID>")) {
                trie = trie.children.get("<UID>");
            }
            else {
                return Strings.EMPTY;
            }
        }
        return StringUtils.isBlank(trie.endpointName) ? Strings.EMPTY : trie.endpointName;
    }

    @Test
    public void test() {
        // Insert patterns
        insert("GET /users", "get_all_users");
        insert("GET /users/<UID>", "get_user");
        insert("GET /users/<UID>/preferences", "get_user_preferences");
        insert("GET /users/<UID>/<UID>", "get_user_posts_in_thread");
        insert("GET /thread/<UID>", "get_thread");
        insert("GET /thread/<UID>/comments", "get_thread_comments");
        insert("GET /thread/<UID>/likes", "get_thread_likes");
        insert("POST /thread", "create_thread");
        insert("DELETE /comments/<UID>", "delete_comment");

        // ✅ Normal cases
        assertEquals("get_all_users", resolveEndpoint("GET /users"));
        assertEquals("get_user", resolveEndpoint("GET /users/abc123"));
        assertEquals("get_user_preferences", resolveEndpoint("GET /users/u567/preferences"));
        assertEquals("get_user_posts_in_thread", resolveEndpoint("GET /users/u1/u2"));
        assertEquals("get_thread_comments", resolveEndpoint("GET /thread/xyz/comments"));
        assertEquals("create_thread", resolveEndpoint("POST /thread"));
        assertEquals("delete_comment", resolveEndpoint("DELETE /comments/5678"));

        // ✅ Edge cases
        assertEquals(Strings.EMPTY, resolveEndpoint("GET /")); // root path not defined
        assertEquals(Strings.EMPTY, resolveEndpoint("PUT /users/abc123")); // unsupported method
        assertEquals(Strings.EMPTY, resolveEndpoint("GET /thread")); // incomplete path
        assertEquals(Strings.EMPTY, resolveEndpoint("GET /thread/123/extra")); // too many segments
        assertEquals(Strings.EMPTY, resolveEndpoint("GET /comments/1234")); // no GET for comments, only DELETE

        // ✅ Wildcard test - multiple UIDs
        assertEquals("get_user_posts_in_thread", resolveEndpoint("GET /users/uid1/uid2"));
        assertEquals("get_user_posts_in_thread", resolveEndpoint("GET /users/abc123/unknown"));
    }

}
