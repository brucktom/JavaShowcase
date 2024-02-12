package Streaming_Preview;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//The following contains several operations on a list by using streaming, lambdas, collecting and mapping

public class Streams_GitLab {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList( "Regensburg", "Basel", "Munich",
                "Bonn", "Hamburg", "Munich", "Berlin" );

        cities.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("___________________");

        cities.stream()
                .distinct()
                .limit(3)
                .forEach(System.out::println);

        System.out.println("____________________");

        System.out.println( "All have at least 6 chars: " +
                cities.stream()
                .allMatch(city -> city.length()>3)
        );

        System.out.println("________________");


        cities.stream()
                .distinct()
                .sorted( (s1,s2) -> Integer.compare(s1.length(), s2.length()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("______");

        cities.stream()
                .distinct()
                .map(s->s.toUpperCase())
                .forEach(System.out::println);

        System.out.println("________");

        int len = 12;

        String cityLength = cities.stream()
                .sorted()
                .filter(city -> city.length() == len)
                .findFirst()
                .orElse("no city name of length " + len);

        System.out.println(cityLength);

        System.out.println("_____________");

        //print name with the longest name

        cities.stream()
                .sorted( (s1,s2) -> -Integer.compare(s1.length(), s2.length()))
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println("_________");

        //print length of longest city name
        cities.stream()
                .map(s-> s.length())
                .min( (s1,s2) -> Integer.compare(s1,s2))
                .ifPresent(System.out::println);

        //reduce list of names to String of their intials
        String c = cities.stream()
                .map(s -> s.toUpperCase())
                .map( s-> s.charAt(0))
                .reduce("", (c1,c2) -> (c1+c2), (c1,c2) -> (c1+c2));
        System.out.println(c);

        System.out.println("_________");

        //compute total sum of string length over all names
        int sum = cities.stream()
                .mapToInt(String::length)
                .sum();
        System.out.println(sum);

        //count number of letters in city names and print table to console sorted
        //by key
        cities.stream()
                .map(s->s.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .forEach(entrySet -> System.out.println(entrySet.getKey() + ": " + entrySet.getValue()) );
    }
}
