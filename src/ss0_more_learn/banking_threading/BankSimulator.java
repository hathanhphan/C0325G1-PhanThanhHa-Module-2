package ss0_more_learn.banking_threading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BankSimulator {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(1000);
        BankSystem system = new BankSystem(account);
        List<String> customers = Arrays.asList("Khách 1", "Khách 2", "Khách 3", "Khách 4", "Khách 5");
        CountDownLatch latch = new CountDownLatch(customers.size());
        boolean useLock = true;
        system.startTransactions(customers, latch, useLock);
        latch.await(7, TimeUnit.SECONDS);
        system.stopSystem();
        system.printReport();
    }
}
