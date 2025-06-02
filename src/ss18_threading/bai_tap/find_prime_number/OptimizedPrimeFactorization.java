package ss18_threading.bai_tap.find_prime_number;

import ss2_loop_in_java.helpers.PrimeNumber;

public class OptimizedPrimeFactorization implements Runnable {
    private final int number;

    public OptimizedPrimeFactorization(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        if (PrimeNumber.isPrimeNumber(number)) {
            System.out.println("OptimizedPrimeFactorization: " + number + " is a prime number");
        } else {
            System.out.println("OptimizedPrimeFactorization: " + number + " is not a prime number");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("OptimizedPrimeFactorization finishes after " + ((endTime - startTime) / 1000) + "s.");
    }
}
