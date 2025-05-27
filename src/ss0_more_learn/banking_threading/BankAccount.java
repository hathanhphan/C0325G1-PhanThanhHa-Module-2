package ss0_more_learn.banking_threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance;
    private Lock lock = new ReentrantLock();
    private List<String> history = new ArrayList<>();

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized boolean withdrawSynchronized(String name, int amount) {
        return withdraw(name, amount);
    }

    public boolean withdrawWithLock(String name, int amount) {
        lock.lock();
        try {
            return withdraw(name, amount);
        } finally {
            lock.unlock();
        }
    }

    private boolean withdraw(String name, int amount) {
        if (balance >= amount) {
            balance -= amount;
            String log = name + " rút " + amount + " | Số dư: " + balance;
            history.add(log);
            System.out.println(log);
            return true;
        } else {
            String log = name + " cố gắng rút " + amount + " nhưng không đủ tiền.";
            System.out.println(log);
            return false;
        }
    }

    public int getBalance() {
        return balance;
    }

    public List<String> getHistory() {
        return history;
    }
}
