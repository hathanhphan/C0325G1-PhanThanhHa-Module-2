package ss0_more_learn.reentant_lock;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment(String name) {
        lock.lock(); // Bắt đầu vùng đồng bộ
        try {
            count++;
            System.out.println(name + " tăng, count = " + count);
        } finally {
            lock.unlock(); // Kết thúc vùng đồng bộ, giải phóng lock
        }
    }

    public void decrement(String name) {
        lock.lock();
        try {
            count--;
            System.out.println(name + " giảm, count = " + count);
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
