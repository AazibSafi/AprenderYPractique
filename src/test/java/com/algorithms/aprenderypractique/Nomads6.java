package com.algorithms.aprenderypractique;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 *  Aazib Safi
 */
public class Nomads6 extends BaseTest {

    @Test
    public void test() throws IOException {
        List<String> usernames = Nomads6.getUsernames(10);
        usernames.forEach(System.out::println);

        System.out.println("---------------------");

        usernames = Nomads6.getUsernames(11);
        usernames.forEach(System.out::println);
    }

    public static List<String> getUsernames(int threshold) throws IOException {
        List<String> usernames = new ArrayList<>();
        int num = 0;
        UserDetails userDetails;
        do {
            num++;
            String url = "https://jsonmock.hackerrank.com/api/article_users?page="+num;
            String responseString = callApi(url);
            userDetails = getResponseObject(responseString, UserDetails.class);

            for (User user : userDetails.data) {
                if(user.submission_count > threshold) {
                    usernames.add(user.username);
                }
            }
        } while(num < userDetails.total_pages);

        return usernames;
    }

    public static String callApi(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        return convertInputStreamToString(responseStream);
    }

    private static String convertInputStreamToString(InputStream inputStream) {
        String newLine = System.getProperty("line.separator");
        String result;
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
            result = lines.collect(Collectors.joining(newLine));
        }

        return result;
    }

    public static <T> T getResponseObject(String responseString, Class<T> responseType) {
//        Below Commented code works in HackerRank
        //Gson g = new Gson();
        //return g.fromJson(responseString, responseType);
        return (T) new Object(); //
    }

}

class UserDetails {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<User> data;
}

class User {
    public int id;
    public String username;
    public String about;
    public int submitted;
    public String updated_at;
    public int submission_count;
    public int comment_count;
    public int created_at;
}