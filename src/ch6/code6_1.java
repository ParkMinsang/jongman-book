package ch6;

public class code6_1 {

    public static void main(String[] args) {
        int recursiveSum = recursiveSum(5);

        System.out.println("recursiveSum = " + recursiveSum);
    }

    public static int recursiveSum(int n) {
        if (n < 1) {
            return 0;
        }

        return n + recursiveSum(n - 1);
    }
}
