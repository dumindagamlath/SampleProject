package examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import model.Institute;
import model.Student;

public class Java8Streams {
    public static void main(String[] args) throws IOException {
        // 1. Integer Stream
        IntStream
                .range(1, 10)
                .forEach(System.out::print);
        System.out.println();

        IntStream
                .rangeClosed(1, 10)
                .forEach(System.out::print);
        System.out.println();

        // 2. Integer Stream with skip
        IntStream
                .range(1, 10)
                .skip(5)
                .forEach(x -> System.out.println(x));
        System.out.println();

        // 3. Integer Stream with sum
        System.out.println(
                IntStream
                        .range(1, 5)
                        .sum());
        System.out.println();

        // 4. Stream.of, sorted and findFirst
        Stream.of("Ava", "Aneri", "Alberto")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        // 5. Stream from Array, sort, filter and print
        String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah"};
        Arrays.stream(names)	// same as Stream.of(names)
                .filter(x -> x.startsWith("S"))
                .sorted()
                .forEach(System.out::println);

        // 6. average of squares of an int array
        Arrays.stream(new int[] {2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        // 7. Stream from List, filter and print
        List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
        people
                .stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("a"))
                .forEach(System.out::println);

        // 8. Stream rows from text file, sort, filter, and print
        Stream<String> bands = Files.lines(Paths.get("src/main/resources/bands.txt"));
        bands
                .sorted()
                .filter(x -> x.length() > 13)
                .forEach(System.out::println);
        bands.close();

        // 9. Stream rows from text file and save to List
        List<String> bands2 = Files.lines(Paths.get("src/main/resources/bands.txt"))
                .filter(x -> x.contains("jit"))
                .collect(Collectors.toList());
        bands2.forEach(x -> System.out.println(x));

        // 10. Stream rows from CSV file and count
        Stream<String> rows1 = Files.lines(Paths.get("src/main/resources/data.txt"));
        int rowCount = (int)rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();

        // 11. Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get("src/main/resources/data.txt"));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
        rows2.close();

        // 12. Stream rows from CSV file, store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get("src/main/resources/data1.txt"));
        //D:\material\java\web\java8examples\src\main\resources\data1.txt
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        rows3.close();
        for (String key : map.keySet()) {
            System.out.println(key + "  " + map.get(key));
        }

        // 13. Reduction - sum
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (a, b) -> a + b);
        System.out.println("Total = " + total);

        // 14. Reduction - summary statistics
        IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics();
        System.out.println(summary);

        List<Institute> instituteList = new ArrayList<Institute>();

        instituteList.add(new Institute("IIM", Arrays.asList("Bangalore", "Ahmedabad", "Kozhikode", "Lucknow")));
        instituteList.add(new Institute("IIT", Arrays.asList("Delhi", "Mumbai", "Kharagpur")));
        instituteList.add(new Institute("NIFT", Arrays.asList("Hyderabad", "Mumbai", "Patna", "Bangalore")));

        //Java 8 Map() : Get names of all institutes

        List<String> namesOfInstitutes = instituteList.stream().map(Institute::getName).collect(Collectors.toList());

        System.out.println(namesOfInstitutes);

        //      If we suppose to extract unique locations of all institutes, using map() will throw an error.
        //      Because, locations are itself wrapped in another List<String> i.e list within a list. Using flatMap() in such scenarios will give correct
        //      result. Let’s see flatMap() in detail.

        //Java 8 FlatMap() : Get unique locations of all institutes

        Set<String> locationsOfInstitutes = instituteList.stream()
                .flatMap(institute -> institute.getLocations().stream())
                .collect(Collectors.toSet());

        //---------------------------------------------------------------------------

        //Get the collection and later convert to stream to process elements
        List<Integer> ints = IntStream.of(1,2,3,4,5).peek(System.out::println)
                .boxed()
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println(ints);

        //Stream operations directly
        Optional<Integer> max = IntStream.of(1,2,3,4,5)
                .boxed()
                .max(Integer::compareTo);

        System.out.println(max.get());
        //-----------------------------------------------------------------------------
        //Stream operations directly
        int[] arr = new int[]{1,2,3,4,5};
        Optional<Integer> maxInt = IntStream.of(arr)
                .boxed()
                .max(Integer::compareTo);

        OptionalInt max3 = IntStream.of(arr).max();

        System.out.println(Arrays.toString(arr));
        System.out.println(maxInt.get());
        System.out.println(max3.getAsInt());

        //-----------------------------------------------------------------------------

        List<Long> longs = LongStream.of(1l,2l,3l,4l,5l)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(longs);

        //-----------------------------------------------------------------------------

        HashMap<String, Integer> map2 = new HashMap<>();

        map2.put("A", 1);
        map2.put("B", 2);
        map2.put("C", 3);

        //1. Map entries
        Consumer<Map.Entry<String, Integer>> action = System.out::println;

        map2.entrySet().forEach(action);

        //2. Map keys
        Consumer<String> actionOnKeys = System.out::println;

        map2.keySet().forEach(actionOnKeys);

        //3. Map values
        Consumer<Integer> actionOnValues = System.out::println;

        map2.values().forEach(actionOnValues);

        //------------------------------------------------------------------------------

        int sum = Arrays.stream(new int[] {7, 5, 9, 2, 8, 1}).reduce(0, (a, b) -> a+b);

        // There is another form of reduce() method which takes no initial value. But returns an Optional object.
        OptionalInt sum2 = Arrays.stream(new int[] {7, 5, 9, 2, 8, 1}).reduce((a, b) -> a+b);

        OptionalInt min = Arrays.stream(new int[] {7, 5, 9, 2, 8, 1}).min();

        OptionalInt max2 = Arrays.stream(new int[] {7, 5, 9, 2, 8, 1}).max();

        OptionalInt max21 = IntStream.of(new int[] {7, 5, 9, 2, 8, 1}).max();

        OptionalInt sum222 = IntStream.of(new int[] {7, 5, 9, 2, 8, 1}).reduce((a, b) -> a+b);



        //--------------------------------------------------------------------------------------

        Supplier<Student> studentSupplier = () -> new Student("name", 1, "Maths", 89.9);

        List<Student> listOfStudents = new ArrayList<Student>();

        listOfStudents.add(studentSupplier.get());

        Function<Student, String> nameFunction = student -> student.getName();

        List<String> studentNames = new ArrayList<String>();

        for (Student student : listOfStudents)
        {
            studentNames.add(nameFunction.apply(student));
        }
    }
}
