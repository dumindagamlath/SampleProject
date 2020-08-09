package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Employee;

public class NameComparator {

    private static List<Employee> getEmployees(){
        List<Employee> employees  = new ArrayList<>();
        employees.add(new Employee(6,"Yash", "Chopra", 25));
        employees.add(new Employee(2,"Aman", "Sharma", 28));
        employees.add(new Employee(3,"Aakash", "Yaadav", 52));
        employees.add(new Employee(5,"David", "Kameron", 19));
        employees.add(new Employee(4,"James", "Hedge", 72));
        employees.add(new Employee(8,"Balaji", "Subbu", 88));
        employees.add(new Employee(7,"Karan", "Johar", 59));
        employees.add(new Employee(1,"Lokesh", "Gupta", 32));
        employees.add(new Employee(9,"Vishu", "Bissi", 33));
        employees.add(new Employee(10,"Lokesh", "Ramachandran", 60));
        return employees;
    }

    public static void main(String args[]) {
        List<Employee> employees  = getEmployees();

        //Compare by Id
        Comparator<Employee> compareById_1 = Comparator.comparing(e -> e.getId());

        Comparator<Employee> compareById_2 = (Employee o1, Employee o2) -> o1.getId().compareTo(o2.getId());

        //Compare by firstname
        Comparator<Employee> compareByFirstName = Comparator.comparing(e -> e.getName());

        //how to use comparator
        Collections.sort(employees, compareById_1);

        //----------------------------------------------------------------------------------

        //Sort all employees by first name
        employees.sort(Comparator.comparing(e -> e.getName()));

        //OR you can use below
        employees.sort(Comparator.comparing(Employee::getName));

        //Let's print the sorted list
        System.out.println(employees);
        //--------------------------------------------------------------------------------
        Comparator<Employee> comparator = Comparator.comparing(e -> e.getName());

        employees.sort(comparator.reversed());

        //Let's print the sorted list
        System.out.println(employees);

        //----------------------------------------------------------------------------------
        //Sorting on multiple fields; Group by.
        Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getName)
                .thenComparing(Employee::getLastName);
        employees.sort(groupByComparator);

        System.out.println(employees);
        //----------------------------------------------------------------------------------

        //Parallel Sorting
        Employee[] employeesArray = employees.toArray(new Employee[employees.size()]);

        //Parallel sorting
        Arrays.parallelSort(employeesArray, groupByComparator);

        System.out.println(employeesArray);
    }
}
