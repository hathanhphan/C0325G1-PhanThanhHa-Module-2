package ss2_loop_in_java.bai_tap;

public class Display20PrimeNumbers {
    public static void main(String[] args) {
        int count = 0;
        int number = 2;
        System.out.print("20 số nguyên tố đầu tiên là: ");
        while (count < 20) {
            if (isPrimeNumber(number)) {
                System.out.printf("%-4d", number);
                count++;
            }
            number++;
        }
    }
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
}
