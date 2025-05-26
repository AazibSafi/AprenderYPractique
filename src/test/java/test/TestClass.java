package test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestClass {

    @Test
    public void test() {
        String p = "/leet/code";
        String[] sp = p.split("/");
        Arrays.stream(sp).forEach(s -> System.out.println("<-->" + s));
    }

    public List<String> toUpper(List<String> list) {
        return list.stream().filter(Objects::nonNull).map(String::toUpperCase).toList();
    }

}
