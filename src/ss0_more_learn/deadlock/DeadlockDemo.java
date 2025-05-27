package ss0_more_learn.deadlock;

public class DeadlockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            synchronized (lockA) {
                System.out.println("Thread 1: Đã giữ lockA, cố lấy lockB...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lockB) {
                    System.out.println("Thread 1: Đã giữ lockB");
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (lockB) {
                System.out.println("Thread 2: Đã giữ lockB, cố lấy lockA...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lockA) {
                    System.out.println("Thread 2: Đã giữ lockA");
                }
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
    }
}
