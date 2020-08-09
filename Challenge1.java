package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Challenge1 {

    private static Predicate<Integer> everyOther = i -> i % 2 == 1;
    public String everySecondChar(String src) {
        StringBuilder srtBuilder = new StringBuilder();
        for (int i = 0; i < src.length() ; i++) {
            if (everyOther.test(i)) {
                srtBuilder.append(src.charAt(i));
            }
        }

        return srtBuilder.toString();
    };

    public static Function<String, String> everySecondCharFn = src -> {
        StringBuilder srtBuilder = new StringBuilder();
        for (int i = 0; i < src.length() ; i++) {
            if (everyOther.test(i)) {
                srtBuilder.append(src.charAt(i));
            }
        }

        return srtBuilder.toString();
    };

    private String everySecondChar(Function<String, String> fn, String arg) {
        return fn.apply(arg);
    }

    private Consumer<String> printVal = s -> everySecondCharFn.apply(s);

    private static Supplier<String> labelILoveJava = () -> "I Love Java";


    public static void main(String[] args) {

//        Runnable runnable = new Runnable() {
//
//            @Override
//            public void run() {
//                String str = "Lets' split this into an arry";
//                String[] splitArr  = str.split(" ");
//                for (String splitStr : splitArr) {
//                    System.out.println(splitStr);
//                }
//            }
//        };
//
//        Consumer<String> splitStr = (String str)-> {
//            String[] splitArr  = str.split(" ");
//            for (String split : splitArr) {
//                System.out.println(split);
//            }
//        };
//
//        Runnable runnable2 = new Runnable() {
//
//            @Override
//            public void run() {
//                String str = "Lets' split this into an arry";
//                splitStr.accept("Lets' split this into an arry");
//            }
//        };
//
//        new Thread(runnable2).start();
//
        Challenge1 challenge1 = new Challenge1();
//        System.out.println(challenge1.everySecondCharFn.apply("123456789"));

//        System.out.println(challenge1.everySecondChar(everySecondCharFn, "123456789"));

//        System.out.println(labelILoveJava.get());

        List<String> names = Arrays.asList(
                "Harry",
                "james",
                "Juan",
                "rose",
                "mathew",
                "Aron",
                "Abi"
        );

        List<String> sortedList = names.stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .sorted()
                .collect(Collectors.toList());

        sortedList.forEach(System.out::println);

        List<String> AList = names.stream()
                .filter(s -> s.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(AList.stream().count());
    }


}
