package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Person;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class FunctionalClass {

    public static boolean GreaterThan3(int number) {
        return (number > 3);
    };

    public static boolean isEven(int number) {
        return (number % 2 == 0);
    };

    public static int doubleIt(int number) {
        return (number * 2);
    };

    public static int totalValues(List<Integer> values, Predicate<Integer> selector) {
        return values.stream().filter(selector)
                .reduce(0, Integer::sum);
    }

    public static int tripleIt(int number) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (number * 3);
    }

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 4, 5, 4, 6, 7, 8, 9);

        List<Person> people = Arrays.asList(new Person("John", 29)
                , new Person("Jill", 45),
                new Person("Jill", 75),
                new Person("Jill", 40),
                new Person("James", 35));

        Predicate<Integer> isGT3 = number -> number > 3;

        Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> (number -> number > pivot);

        System.out.println(values.stream().filter(isGreaterThan.apply(3))
                .filter(FunctionalClass::isEven)
                .map(FunctionalClass::doubleIt)
                .findFirst()
                .get());


        System.out.println(totalValues(values, e-> true));
        System.out.println(totalValues(values, e-> e % 2 == 0));
        //Mixing object composition + functional composition

        System.out.println(values.parallelStream()
                .mapToInt(FunctionalClass::tripleIt)
                .sum());

        Thread thread = new Thread(() -> System.out.println("In another thread"));
        thread.start();

        values.forEach(value -> System.out.println(value));

        values.forEach(System.out::println);

        values.stream()
                .map(String::valueOf)
                .forEach(System.out::println);

        values.stream()
          //      .map(String::valueOf)
                .map(e -> Integer.toString(e))
              //  .map(e -> e.toString())
                .forEach(System.out::println);

        System.out.println(values.stream()
        .reduce(0, (total, e)-> Integer.sum(total, e)));

        System.out.println(values.stream()
                .reduce(0, Integer::sum));

        System.out.println(values.stream()
                .map(String::valueOf)
                .reduce("", String::concat));

        // Method references cannot be used when there is a calculation
        // When there is a static method as well as instance method i.e Integer.toString()

        System.out.println(values
                .stream()
                .filter(e -> e % 2 == 0)
                .reduce(0, (calc, e)->(calc + e))
                );

        System.out.println(values
                .stream()
                .filter(e -> e % 2 == 0)
                .mapToDouble(e -> e * 2.0)
                .sum()
        );

        List<Double> doubleOfInt = values
                .stream()
                .filter(e-> e % 2 == 0)
                .map(e -> e * 2.0)
                .collect(toList());

//        List<Double> doubleOfInt2 = new ArrayList<Double>();
//        values
//                .stream()
//                .filter(e-> e % 2 == 0)
//                .map(e -> e * 2.0)
//                .forEach(e -> doubleOfInt2.add(e));
        // Shared mutability will create issues

//        System.out.println(people
//                .stream()
//                .collect(toMap(p -> p.getName() + p.getAge(), p-> p)));

        System.out.println(people
            .stream()
            .collect(groupingBy(Person::getName, mapping(Person::getAge, Collectors.toList()))));

        int k=121;
        int n=51;
        System.out.println(Stream.iterate(k, e->e+ 1)
                .filter(e->e % 2== 0)
                .filter(e-> Math.sqrt(e) > 20)
                .mapToInt(e->e * 2)
                .limit(n)
                .sum());


    }
}
