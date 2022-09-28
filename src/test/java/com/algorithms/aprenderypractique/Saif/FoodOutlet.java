package com.algorithms.aprenderypractique.Saif;

import com.algorithms.aprenderypractique.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 *  Aazib Safi
 */
public class FoodOutlet extends BaseTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        List<String> outletNames = FoodOutlet.getRelevantFoodOutlets("Denver", 50);
        outletNames.forEach(System.out::println);

        System.out.println("---------------------");

        outletNames = FoodOutlet.getRelevantFoodOutlets("Houston", 30);
        outletNames.forEach(System.out::println);
    }

    public static List<String> getRelevantFoodOutlets(String city, int maxCount) throws IOException {
        List<String> outletNames = new ArrayList<>();
        int num = 0;
        Response response;
        do {
            num++;
            String url = "https://jsonmock.hackerrank.com/api/food_outlets?city="+city+"&page="+num;
            response = callApi(url);

            for (Outlet outlet : response.data) {
                if(outlet.estimated_cost <= maxCount) {
                    outletNames.add(outlet.name);
                }
            }
        } while(num < response.total_pages);

        return outletNames;
    }

    public static Response callApi(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream inputStream = connection.getInputStream();

        String text = convertInputStreamToString(inputStream);
        return fromJson(text);
    }

//  Gson works on HackerRank (according to Google)
    public static Response fromJson(String json) {
        Gson g = new Gson();
        return g.fromJson(json, Response.class);
    }

//  Object Mapper doesn't work on HackerRank - tried many times
    public static Response fromJson(InputStream inputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, Response.class);
    }

    public static String convertInputStreamToString(InputStream inputStream) {
        return new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

}

class Response {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<Outlet> data;
}

class Outlet {
    public int id;
    public String city;
    public String name;
    public int estimated_cost;
    public UserRating user_rating;
}

class UserRating {
    public double average_rating;
    public int votes;
}
