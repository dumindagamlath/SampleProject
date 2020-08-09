package examples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FunctionalInterface {
    public static void main(String args[]) {
        Map<String, Integer> nameMap = new HashMap<>();
        Integer value1 = nameMap.computeIfAbsent("John", s -> s.length());
        System.out.println(value1);

        Integer value2 = nameMap.computeIfAbsent("John", String::length);

        // The Function interface has also a default compose method that allows to combine several
        // functions into one and execute them sequentially:

        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";

        Function<Integer, String> quoteIntToString = quote.compose(intToString);

        // assertEquals("'5'", quoteIntToString.apply(5));
        System.out.println(quoteIntToString.apply(5));
    }
}
