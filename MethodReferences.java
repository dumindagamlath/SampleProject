package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MethodReferences {
    public static void main(String args[]) {
        List<Integer> integers = Arrays.asList(1, 12, 433, 5);

        Optional<Integer> max = integers.stream().reduce(Math::max);

        max.ifPresent(System.out::println);

        //--------------------------------------------------------

        Comparator<String> comparatorStr = (s1, s2) -> s1.compareTo(s2);

        List<String> strings = Arrays
                .asList("how", "to", "do", "in", "java", "dot", "com");

        List<String> sorted = strings
                .stream()
                .sorted(comparatorStr)
                .collect(Collectors.toList());

        System.out.println(sorted);

        List<String> sortedAlt = strings
                .stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        System.out.println(sortedAlt);

        //---------------------------------------------------------

        List<Integer> integers2 = IntStream
                .rangeClosed(1, 100)
                .boxed().collect(Collectors.toList());
                //.collect(Collectors.toCollection( ArrayList::new ));

        Optional<Integer> max2 = integers2.stream().reduce(Integer::sum);

        max2.ifPresent(System.out::println);
    }
}
