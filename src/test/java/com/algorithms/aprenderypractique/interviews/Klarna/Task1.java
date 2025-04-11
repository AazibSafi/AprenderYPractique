package com.algorithms.aprenderypractique.interviews.Klarna;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Ignore
public class Task1 extends BaseTest {

    @Test
    public void test() {
        assertThat(Task1.ingredients("Classic"))
                .isEqualTo("banana","honey","mango","peach","pineapple","strawberry");

        assertThat(Task1.ingredients("Classic,-strawberry"))
            .isEqualTo("banana","honey","mango","peach","pineapple");
    }

    public static String ingredients(String order) {
        if(Objects.isNull(order)) {
            throw new IllegalArgumentException();
        }

        Map<String, List<String>> smoothieIngredients = getSmoothieIngredients();

        String[] menu = order.split(",");

        List<String> allergicMenuList = parseMenu(Arrays.copyOfRange(menu, 1, menu.length));

        List<String> ingredients = smoothieIngredients.get(menu[0]);

        if(!Objects.isNull(ingredients) && ingredients.isEmpty()) {
            throw new IllegalArgumentException();
        }

        List<String> filteredIngredients = ingredients.stream()
                .filter(item -> !allergicMenuList.contains(item))
                .collect(Collectors.toList());

        return String.join(",",filteredIngredients);
    }

    public static List<String> parseMenu(String[] menu) {
        List<String> allergicMenuList = new ArrayList();

        Arrays.stream(menu).forEach(menuItem -> {
            if(!menuItem.startsWith("-")) {
                throw new IllegalArgumentException();
            }
            allergicMenuList.add(menuItem.substring(1));
        });

        return allergicMenuList;
    }

    public static Map<String, List<String>> getSmoothieIngredients() {
        Map<String, List<String>> smoothieIngredients = new LinkedHashMap<>();
        smoothieIngredients.put("Classic", Arrays.asList("banana","honey","mango","peach","pineapple","strawberry"));
        smoothieIngredients.put("Freezie", Arrays.asList("blackberry","black currant","blueberry","frozen","grape juice","yogurt"));
        smoothieIngredients.put("Greenie", Arrays.asList("avocado","apple juice","ice","green apple","lime","spinach"));
        smoothieIngredients.put("Just Desserts", Arrays.asList("banana","cherry","chocolate","ice cream","peanut"));
        return smoothieIngredients;
    }

}
