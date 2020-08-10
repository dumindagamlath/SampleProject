package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class JoinerSplitter {


    public static void main(String args[]) {
        List<Integer> intList = new ArrayList();
        intList.add(1);
        intList.add(3);
        List<Integer> integerList = Arrays.asList(1,2,3);
        Integer[] integerArray = new Integer[]{};

        int[] intArry = intList.stream().mapToInt(Integer::intValue).toArray();

        System.out.println(integerList);
        System.out.println(Arrays.toString(intArry));


        splitToListOfChar("duminda");
    }

    public static String join(String[] arrayOfString){
        return Arrays.asList(arrayOfString)
                .stream()
                //.map(...)
                .collect(Collectors.joining(","));
    }

    public static String joinWithPrefixPostfix(String[] arrayOfString){
        return Arrays.asList(arrayOfString)
                .stream()
                //.map(...)
                .collect(Collectors.joining(",","[","]"));
    }

    public static List<String> split(String str){
        return Stream.of(str.split(","))
                .map (elem -> new String(elem))
                .collect(Collectors.toList());
    }

    public static List<Character> splitToListOfChar(String str) {
        return str.chars()
                .mapToObj(item -> (char) item)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public static Map<String, String> arrayToMap(String[] arrayOfString) {
        return Arrays.asList(arrayOfString)
                .stream()
                .map(str -> str.split(":"))
                .collect(toMap(str -> str[0], str -> str[1]));
    }


}
