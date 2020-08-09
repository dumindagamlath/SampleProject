package examples;

public class ReverseInteger {

    public static void main(String args[]) {
        int K = 8976;
        System.out.print(reverseInt(K));

    }

    public static int reverseInt(int K) {
        int val = -1;
        String numStr = String.valueOf(K);
        StringBuilder buildStr = new StringBuilder(numStr).reverse();
        val = Integer.parseInt(buildStr.toString());
        return val;
    }
}
