package examples;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import model.Rating;
import model.Review;
import model.User;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class StreamReduce {

    public static void main(String args[]) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result1 = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        //assertThat(result).isEqualTo(21);

        int result2 = numbers.stream().reduce(0, Integer::sum);

        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String resultStr1 = letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element);
        //assertThat(result).isEqualTo("abcde");

        String resultStr2 = letters.stream().reduce("", String::concat);

        String result3 = letters
                .stream()
                .reduce(
                        "", (partialString, element) -> partialString.toUpperCase() + element.toUpperCase());
        //assertThat(result).isEqualTo("ABCDE");

        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);

        User john = new User("John", 30);
        john.getRating().add(new Review(5, ""));
        john.getRating().add(new Review(3, "not bad"));
        User julie = new User("Julie", 35);
        john.getRating().add(new Review(4, "great!"));
        john.getRating().add(new Review(2, "terrible experience"));
        john.getRating().add(new Review(4, ""));
        List<User> users = Arrays.asList(john, julie);
//        int computedAges =
//                users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge());

        int result = users.stream()
                .reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
        //assertThat(result).isEqualTo(65);

//        To put it simply,  if we use sequential streams and the types of the accumulator arguments and the types of its
//        implementation match, we don't need to use a combiner.

        Rating averageRating = users.stream()
                .reduce(new Rating(),
                        (rating, user) -> Rating.average(rating, user.getRating()),
                        Rating::average);
    }

    public static int divideListElements(List<Integer> values, int divider) {
        return values.stream()
                .reduce(0, (a, b) -> {
                    try {
                        return a / divider + b / divider;
                    } catch (ArithmeticException e) {
                        LOGGER.log(Level.INFO, "Arithmetic Exception: Division by Zero");
                    }
                    return 0;
                });
    }

    private static int divide(int value, int factor) {
        int result = 0;
        try {
            result = value / factor;
        } catch (ArithmeticException e) {
            LOGGER.log(Level.INFO, "Arithmetic Exception: Division by Zero");
        }
        return result;
    }

    public static int divideListElements2(List<Integer> values, int divider) {
        return values.stream().reduce(0, (a, b) -> divide(a, divider) + divide(b, divider));
    }

//    @Test
//    public void givenDepartmentList_thenEmployeeListIsFilteredCorrectly() {
//        Integer expectedId = 1002;
//
//        populate(emplList, deptList);
//
//        List<Employee> filteredList = emplList.stream()
//                .filter(empl -> deptList.stream()
//                        .anyMatch(dept ->
//                                dept.getDepartment().equals("sales") &&
//                                        empl.getEmployeeId().equals(dept.getEmployeeId())))
//                .collect(Collectors.toList());
//
//        assertEquals(1, filteredList.size());
//        assertEquals(expectedId, filteredList.get(0)
//                .getEmployeeId());
//    }
}
