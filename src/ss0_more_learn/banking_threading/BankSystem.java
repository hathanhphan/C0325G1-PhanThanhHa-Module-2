package ss0_more_learn.banking_threading;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankSystem {
    private volatile boolean running = true;
    private BankAccount account;
    private ConcurrentHashMap<String, Integer> customerWithdrawals = new ConcurrentHashMap<>();
    private volatile boolean emergencyStop = false;  // Biến cờ hiệu cho dừng đột ngột

    public BankSystem(BankAccount account) {
        this.account = account;
    }

    public void stopSystem() {
        running = false;
    }

    public void stopEmergency() {
        emergencyStop = true;
        running = false;
        System.out.println("\n*** DỪNG KHẨN CẤP TOÀN BỘ HỆ THỐNG DO PHÁT HIỆN GIAO DỊCH BẤT THƯỜNG! ***\n");
    }

    @SuppressWarnings("BusyWait")
    public void startTransactions(List<String> customers, CountDownLatch latch, boolean useLock) {
        ExecutorService executor = Executors.newFixedThreadPool(customers.size());
        for (String customer : customers) {
            executor.submit(() -> {
                Random random = new Random();
                while (running && !emergencyStop) {
                    int amount = 10 + random.nextInt(90);;
                    boolean success;
                    if (useLock) {
                        success = account.withdrawWithLock(customer, amount);
                    } else {
                        success = account.withdrawSynchronized(customer, amount);
                    }
                    if (success) {
                        customerWithdrawals.merge(customer, amount, Integer::sum);
                    }
                    try {
                        Thread.sleep(100 + random.nextInt(200));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    if (account.getBalance() <= 0) {
                        break;
                    }
                }
                latch.countDown();
            });
        }
        executor.shutdown();
    }

    public void printReport() {
        System.out.println("\n--------------Báo cáo giao dịch--------------");
        customerWithdrawals.forEach((name, sum) -> {
            System.out.println(name + " đã rút tổng cộng: " + sum);
        });
        System.out.println("\nLịch sử giao dịch:");
        account.getHistory().forEach(System.out::println);
        System.out.println("\nSố dư tài khoản còn lại: " + account.getBalance());
    }
}
