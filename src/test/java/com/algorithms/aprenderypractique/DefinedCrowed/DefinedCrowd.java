package com.algorithms.aprenderypractique.DefinedCrowed;

import com.algorithms.aprenderypractique.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
 *  Aazib Safi
 */
public class DefinedCrowd extends BaseTest {

    @Test
    public void test() throws IOException {
        List<String> titles = DefinedCrowd.getArticleTitles("epaga");
        titles.forEach(System.out::println);

        System.out.println("---------------------");

        titles = DefinedCrowd.getArticleTitles("saintamh");
        titles.forEach(System.out::println);
    }

    static ObjectMapper mapper = new ObjectMapper();

    public static List<String> getArticleTitles(String author) throws IOException {
        // WARNING: HackerRank only supports json simple and Gson
        List<String> titles = new ArrayList<>();
        int num = 0;
        Response response;
        do {
            num++;
            String url = "https://jsonmock.hackerrank.com/api/articles?author="+author+"&page="+num;
            response = callUrl(url);
            for (Article article : response.data) {
                if(article.title != null) {
                    titles.add(article.title);
                }
                else if(article.story_title != null) {
                    titles.add(article.story_title);
                }
            }
        } while(num < response.total_pages);


        return titles;
    }

    public static Response callUrl(String uri) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
         return mapper.readValue(responseStream, Response.class);
    }

}

class Response {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<Article> data;
}

class Article {
    public String title;
    public String url;
    public String author;
    public Long num_comments;
    public Long story_id;
    public String story_title;
    public String story_url;
    public Long parent_id;
    public Long created_at;
}
