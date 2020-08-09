package examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LowestInteger {
    public int solution(int[] A){
        int num = 1;

        int[] sortedArr = Arrays.stream(A).filter(i -> i > 0)
                .sorted()
                .toArray();

        int MIN = 1;

        for (int i =0; i < sortedArr.length; i++) {
            if (sortedArr[0] > MIN) {
                return MIN;
            } else if (i == sortedArr.length - 1) {
                return ++MIN;
            }
        }

        return num;
    }

    public static void main(String args[]) {
        int[] arr = new int[] {1, 5, 2, -2};

        LowestInteger codility = new LowestInteger();
        System.out.println(codility.solution(arr));

    }
}
