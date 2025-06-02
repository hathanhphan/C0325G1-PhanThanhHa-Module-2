package ss18_threading.bai_tap.find_prime_number;

public class Main {
    public static void main(String[] args) {
        Thread lazyThread = new Thread(new LazyPrimeFactorization(Integer.MAX_VALUE));
        Thread optimizedThread = new Thread(new OptimizedPrimeFactorization(Integer.MAX_VALUE));
        lazyThread.start();
        optimizedThread.start();
    }
}
