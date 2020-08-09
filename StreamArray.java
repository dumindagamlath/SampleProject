package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import model.Employee;
import model.Item;

public class StreamArray {
    public static void main(String[] args) {
        // Convert stream of strings to array
        Stream<String> tokenStream = Arrays.asList("A", "B", "C", "D").stream();  //stream

        String[] tokenArray = tokenStream.toArray(String[]::new);   //array

        System.out.println(Arrays.toString(tokenArray));

        //-----------------------------------------------------------------

        // Convert infinite stream to array
        IntStream infiniteNumberStream = IntStream.iterate(1, i -> i+1);

        int[] intArray = infiniteNumberStream.limit(10)
                .toArray();

        System.out.println(Arrays.toString(intArray));

        //-----------------------------------------------------------------

        // Infinite stream to array to Integers – Boxed stream
        // Convert infinite stream to array
        IntStream infiniteBoxedNumberStream = IntStream.iterate(1, i -> i+1);

        Integer[] integerArray = infiniteBoxedNumberStream.limit(10)
                .boxed()
                .toArray(Integer[]::new);

        System.out.println(Arrays.toString(integerArray));

        //----------------------------------------------------------------

        List<Employee> employeeList = new ArrayList<>(Arrays.asList(
                new Employee(1, "A", 100.00),
                new Employee(2, "B", 200.00),
                new Employee(3, "C", 300.00),
                new Employee(4, "D", 400.00),
                new Employee(5, "E", 500.00),
                new Employee(6, "F", 600.00)));

        Employee[] employeesArray = employeeList.stream()
                .filter(e -> e.getSalary() < 400.00)
                .toArray(Employee[]::new);

        List<Employee> employeeList2 = employeeList.stream()
                .filter(e -> e.getSalary() < 400.00)
                .collect(Collectors.toList());

        System.out.println(Arrays.toString(employeesArray));

        System.out.println(employeeList2);

        //--------------------------------------------------
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemList.add(new Item(i));
        }

        Predicate<Item> isQualified = item -> item.isQualified();

        itemList.stream()
                .filter(isQualified)
                .forEach(item -> item.operate());

        itemList.removeIf(isQualified);


        List<Item> operatedList = new ArrayList<>();
        itemList.stream()
                .filter(item -> item.isQualified())
                .forEach(item -> {
                    item.operate();
                    operatedList.add(item);
                });
        itemList.removeAll(operatedList);
    }
}
