package examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OddOccurency {
    BiFunction<IntStream, Integer, Long> counter = (list, x)  ->  list
            .filter(i -> i == x).count();
    public int solution1(int[] A) {
        int length = A.length;
        OptionalInt ret = IntStream.rangeClosed(0, length)
                .filter(i -> counter.apply(IntStream.of(A), A[i]) == 1)
                .findFirst();
        if (ret.isPresent()) {
            return A[ret.getAsInt()];
        }

        return -1;
    }

    public int solution(int[] A) {
        int length = A.length;
        List<Integer> arrays = new ArrayList<Integer>();
        IntStream.range(0, length)
                .forEach(i -> {
                    int index = arrays.indexOf(A[i]);
                    if (index > -1) {
                        arrays.remove(index);
                    } else {
                        arrays.add(A[i]);
                    }
                });

        return arrays.get(0);
    }

    public int solution2(int[] A) {
        int value = -1;
        Map<Integer, List<Integer>> groupNumbers =
                IntStream.of(A)
                        .boxed()
                        .collect(Collectors.groupingBy(Integer::intValue));
        Optional<Integer> valueInt = groupNumbers.entrySet()
                .stream()
                .filter(k -> k.getValue().size() % 2 != 0 )
                .map(k-> k.getKey()).findFirst();
        if (valueInt.isPresent()) {
            value = valueInt.get();
        }

        return value;
    }

    public static void main(String args[]) {
        int[] array = new int[] {2, 3, 2, 10, 10, 5, 5, 9, 9, 10, 10};
        System.out.println(new OddOccurency().solution2(array));
    }
}
