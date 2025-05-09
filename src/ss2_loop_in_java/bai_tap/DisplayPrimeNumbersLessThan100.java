package ss2_loop_in_java.bai_tap;

import ss2_loop_in_java.helpers.PrimeNumber;

public class DisplayPrimeNumbersLessThan100 {
    public static void main(String[] args) {
        System.out.print("Dãy số nguyên tố nhỏ hơn là: ");
        for (int i = 2; i < 100; i++) {
            if (PrimeNumber.isPrimeNumber(i)) {
                System.out.printf("%-4d", i);
            }
        }
    }
}
