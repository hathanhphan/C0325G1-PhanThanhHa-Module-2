package ss0_more_learn.reentant_lock;

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable incTask = () -> {
            for (int i = 0; i < 5; i++) {
                counter.increment("Thread-Inc");
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        };
        Runnable decTask = () -> {
            for (int i = 0; i < 5; i++) {
                counter.decrement("Thread-Dec");
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        };

        Thread t1 = new Thread(incTask);
        Thread t2 = new Thread(decTask);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count = " + counter.getCount());
    }
}
