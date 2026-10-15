package org.example;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompressOverDelete1 {
    public static void main(String[] args) {
        String input = "aaaabbcdddeffffff";
        Stream<String> res = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter( e -> e.getValue() > 1)
                .map(e-> e.getKey().concat(e.getValue().toString()));
        res.forEach(System.out::println);
        System.out.println("************************" );
        String res2 = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                //.filter( e -> e.getValue() > 1)
                .map(e-> e.getKey().concat(e.getValue() ==1? "":e.getValue().toString()))
                //.reduce("", (e1, e2) -> String.join("", e1+e2));
                        .reduce("", (s1, s2) -> s1.concat(s2));
        System.out.println("res2 = " + res2);

    }
}
