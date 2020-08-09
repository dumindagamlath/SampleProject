package examples;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class IntArray {

//    public int[] solution(int[] A, int K) {
//        int length = A.length;
//        int[] arr = new int[length];
//        IntStream.range(0, length)
//                .forEach(i -> {
//                    int index = K + i;
//                    if (indexCheck.test(length, index)) {
//                        arr[index] = A[i];
//                    } else {
//                        arr[indexFunction.apply(index, length)] = A[i];
//                    }
//                });
//
//        return arr;
//    }
//
//    BiPredicate<Integer, Integer> indexCheck = (i1, i2) -> i1 > i2;
//    BiFunction<Integer, Integer, Integer> indexFunction = (i1, i2)-> i1 % i2;
//
//    Object[] invert(int[] arr) {
//        return IntStream.rangeClosed(1, arr.length)
//                .mapToObj(i-> arr[arr.length - i])
//                .toArray();
//    }
//
//    public int solution(int[] A) {
//        int[] Asorted = Arrays.stream(A).filter(i -> i > 0).sorted().distinct().toArray();
//        int MIN = 1;
//        for (int i = 0; i < Asorted.length; i++) {
//            if (Asorted[i] > MIN) {
//                return MIN;
//            }
//
//            MIN++;
//        }
//        return MIN;
//    }

    public static int solution(int N) {
        // write your code in Java SE 8
        int val = N;

        int[] intTab = String.valueOf(val)
                .chars()
                .map(Character::getNumericValue)
                .peek(System.out::println)
                .toArray();

        int[] sorted = Arrays.stream(intTab).sorted().toArray();
        System.out.println(Arrays.toString(sorted));
        String value = Arrays.stream(sorted).mapToObj(i -> String.valueOf(i)).reduce("", (str1, str2)-> str2+str1);
        return Integer.valueOf(value);

    }

    public int solution(int[] X, int[] Y) {
        int MAX = 1;

        int[] Xpath = Arrays.stream(X).sorted().distinct().toArray();
        for (int i = 1; i < Xpath.length ; i++) {
            int len = Xpath[i] - Xpath[i-1];
            if (len > MAX) {
                MAX = len;
            }
        }

        return MAX;
    }

    public static void main(String args[]) {
        int[] X = new int[] {1,8,7,3,4,1,8};
        int[] Y = new int[] {6,4,1, 8,5,1,7};
        int k = 55317;

        IntArray obj = new IntArray();
//        System.out.println(Arrays.toString(obj.solution(array, k)));
//        System.out.println(Arrays.toString(obj.invert(array)));

//        System.out.println(obj.solution(array));

        //System.out.println(obj.solution(X, Y));

        //System.out.println(k & 1);

        System.out.println(solution(k));
    }

    public static int reverse(int input) {
        long reversed = 0;
        while (input != 0) {
            reversed = reversed * 10 + input % 10;
            System.out.println("reversed ->"+reversed);
            input /= 10;
            System.out.println("input ->"+input);
            if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return (int)reversed;
    }

    public static int reverseLeet(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
