package ss2_loop_in_java.bai_tap;

import ss2_loop_in_java.helpers.PrimeNumber;

public class Display20PrimeNumbers {
    public static void main(String[] args) {
        int count = 0;
        int number = 2;
        System.out.print("20 số nguyên tố đầu tiên là: ");
        while (count < 20) {
            if (PrimeNumber.isPrimeNumber(number)) {
                System.out.printf("%-4d", number);
                count++;
            }
            number++;
        }
    }
}
