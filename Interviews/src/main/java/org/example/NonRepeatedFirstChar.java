package org.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NonRepeatedFirstChar {

    public static void main(String[] args) {
        String input = "989fhKkjnluoe09808et3wr3eafkfK gndtgawedcvncvhnsrt";
        Character result = input.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(c->c, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("result = " + result);
    }
}
