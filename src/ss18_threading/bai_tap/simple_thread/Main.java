package ss18_threading.bai_tap.simple_thread;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new NumberGenerator(), "Thread 1");
        Thread thread2 = new Thread(new NumberGenerator(), "Thread 2");
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();
        thread1.notify();
    }
}
