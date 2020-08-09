package examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DemoCodility {
    public int solution(String S) {
        // write your code in Java SE 8
        int index = -1;
        List<Integer> map = new ArrayList<Integer>();
        int length = S.length();
        for (int i = 0; i < length - 1; i++) {
            int next = S.lastIndexOf(S.substring(i, i+2));
            if (next > i) {
                index = next - i;
                map.add(index);
            }
        }

        Optional<Integer> optInt = map.stream().max(Integer::compareTo);
        if (optInt.isPresent()) {
            index = optInt.get();
        }

        return index;
    }

    public static void main(String args[]) {
        String pattern = "aakmaakmakda";

        DemoCodility codility = new DemoCodility();
        System.out.println(codility.solution(pattern));

    }
}
