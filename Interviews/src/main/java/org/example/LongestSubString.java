package org.example;

import java.util.Comparator;
import java.util.stream.IntStream;

public class LongestSubString {
    public static void main(String[] args) {
        String input = "hielonnsbhvjpkcsmlsdkmckjhigc";
        int n = input.length();
        String name = IntStream.range(0,n).boxed()
                .flatMap( i -> IntStream.range( i+1, n)
                        .mapToObj(j -> input.substring(i,j)))
                .filter(e -> (e.chars().distinct().count() == e.length()))
                //.max((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println(name);
    }
}
