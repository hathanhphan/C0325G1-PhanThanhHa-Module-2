package ss18_threading.bai_tap.find_prime_number;

import ss2_loop_in_java.helpers.PrimeNumber;

public class LazyPrimeFactorization implements Runnable {
    private final int number;

    public LazyPrimeFactorization(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        if (PrimeNumber.isLazyPrimeNumber(number)) {
            System.out.println("LazyPrimeFactorization: " + number + " is a prime number");
        } else {
            System.out.println("LazyPrimeFactorization: " + number + " is not a prime number");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("LazyPrimeFactorization finishes after " + ((endTime - startTime) / 1000) + "s.");
    }
}
