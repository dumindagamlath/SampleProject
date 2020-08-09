package examples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SumTwoArrays {

    public static int[] solution(int[] elements, int sum) {
        int[] array = {-1, -1};
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int index = 0; index < elements.length; index++) {
            int diff = sum - elements[index];
            Optional<Map.Entry<Integer, Integer>> entryItem = map.entrySet()
                    .stream()
                    .filter(k-> k.getValue() + diff == sum)
                    .findFirst();
            if (entryItem.isPresent()) {
                array[0] = entryItem.get().getKey();
                array[1] = index;
            } else {
                map.put(index, diff);
            }
        }

        return array;
    }

    public static int[] solution2(int[] elements, int sum) {
        int[] array = {-1, -1};
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int index = 0; index < elements.length; index++) {
            int diff = sum - elements[index];
            if (map.containsKey(diff)) {
                array[0] = map.get(diff).intValue();
                array[1] = index;
            } else {
                map.put(elements[index], index);
            }
        }

        return array;
    }


    public static void main(String args[]) {
        int[] nums = {1, 2, 5, 7, 8};
        int K=12;
        System.out.println(Arrays.toString(solution2(nums, K)));
    }
}
