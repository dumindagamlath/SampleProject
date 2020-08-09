package examples;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public class DemoCodilityMinimumPossible {
    public int solution(int[] A) {
        int sum = 0;

        for (int i = 0; i < A.length; i++) {
            if (even.test(i)) {
                sum = sum + multiEven.apply(A[i]);
            } else {
                sum = sum + A[i];
            }
        }


        return sum;
    }
    public Predicate<Integer> even = i -> i % 2 == 0;
    public IntFunction<Integer> multiEven = i -> i * -1;

    public static void main(String args[]) {
        int[] arr = new int[] {1, 5, 2, -2};

        DemoCodilityMinimumPossible codility = new DemoCodilityMinimumPossible();
        System.out.println(codility.solution(arr));

    }
}
