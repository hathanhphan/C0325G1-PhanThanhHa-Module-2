package ss2_loop_in_java.helpers;

public class PrimeNumber {
    public static boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }
        int length = (int) Math.sqrt(number);
        for (int i = 2; i <= length; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLazyPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
